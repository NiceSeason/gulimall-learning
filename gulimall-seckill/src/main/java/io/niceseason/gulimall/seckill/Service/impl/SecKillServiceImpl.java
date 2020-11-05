package io.niceseason.gulimall.seckill.Service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.niceseason.common.to.mq.SeckillOrderTo;
import io.niceseason.common.utils.R;
import io.niceseason.common.vo.MemberResponseVo;
import io.niceseason.gulimall.seckill.feign.CouponFeignService;
import io.niceseason.gulimall.seckill.feign.ProductFeignService;
import io.niceseason.gulimall.seckill.Service.SecKillService;
import io.niceseason.gulimall.seckill.interceptor.LoginInterceptor;
import io.niceseason.gulimall.seckill.to.SeckillSkuRedisTo;
import io.niceseason.gulimall.seckill.vo.SeckillSessionWithSkusVo;
import io.niceseason.gulimall.seckill.vo.SkuInfoVo;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service("SecKillService")
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //K: SESSION_CACHE_PREFIX + startTime + "_" + endTime
    //V: sessionId+"-"+skuId的List
    private final String SESSION_CACHE_PREFIX = "seckill:sessions:";

    //K: 固定值SECKILL_CHARE_PREFIX
    //V: hash，k为sessionId+"-"+skuId，v为对应的商品信息SeckillSkuRedisTo
    private final String SECKILL_CHARE_PREFIX = "seckill:skus";

    //K: SKU_STOCK_SEMAPHORE+商品随机码
    //V: 秒杀的库存件数
    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";    //+商品随机码





    @Override
    public void uploadSeckillSkuLatest3Days() {
        R r = couponFeignService.getSeckillSessionsIn3Days();
        if (r.getCode() == 0) {
            List<SeckillSessionWithSkusVo> sessions = r.getData(new TypeReference<List<SeckillSessionWithSkusVo>>() {
            });
            //在redis中分别保存秒杀场次信息和场次对应的秒杀商品信息
            saveSecKillSession(sessions);
            saveSecKillSku(sessions);
        }
    }

    @Override
    public List<SeckillSkuRedisTo> getCurrentSeckillSkus() {
        Set<String> keys = redisTemplate.keys(SESSION_CACHE_PREFIX + "*");
        long currentTime = System.currentTimeMillis();
        for (String key : keys) {
            String replace = key.replace(SESSION_CACHE_PREFIX, "");
            String[] split = replace.split("_");
            long startTime = Long.parseLong(split[0]);
            long endTime = Long.parseLong(split[1]);
            //当前秒杀活动处于有效期内
            if (currentTime > startTime && currentTime < endTime) {
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
                List<SeckillSkuRedisTo> collect = range.stream().map(s -> {
                    String json = (String) ops.get(s);
                    SeckillSkuRedisTo redisTo = JSON.parseObject(json, SeckillSkuRedisTo.class);
                    return redisTo;
                }).collect(Collectors.toList());
                return collect;
            }
        }
        return null;
    }

    @Override
    public SeckillSkuRedisTo getSeckillSkuInfo(Long skuId) {
        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
        Set<String> keys = ops.keys();
        for (String key : keys) {
            if (Pattern.matches("\\d-" + skuId,key)) {
                String v = ops.get(key);
                SeckillSkuRedisTo redisTo = JSON.parseObject(v, SeckillSkuRedisTo.class);
                //当前商品参与秒杀活动
                if (redisTo!=null){
                    long current = System.currentTimeMillis();
                    //当前活动在有效期，暴露商品随机码返回
                    if (redisTo.getStartTime() < current && redisTo.getEndTime() > current) {
                        return redisTo;
                    }
                    redisTo.setRandomCode(null);
                    return redisTo;
                }
            }
        }
        return null;
    }

    @Override
    public String kill(String killId, String key, Integer num) throws InterruptedException {
        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
        String json = ops.get(killId);
        String orderSn = null;
        if (!StringUtils.isEmpty(json)){
            SeckillSkuRedisTo redisTo = JSON.parseObject(json, SeckillSkuRedisTo.class);
            //1. 验证时效
            long current = System.currentTimeMillis();
            if (current >= redisTo.getStartTime() && current <= redisTo.getEndTime()) {
                //2. 验证商品和商品随机码是否对应
                String redisKey = redisTo.getPromotionSessionId() + "-" + redisTo.getSkuId();
                if (redisKey.equals(killId) && redisTo.getRandomCode().equals(key)) {
                    //3. 验证当前用户是否购买过
                    MemberResponseVo memberResponseVo = LoginInterceptor.loginUser.get();
                    long ttl = redisTo.getEndTime() - System.currentTimeMillis();
                    //3.1 通过在redis中使用 用户id-skuId 来占位看是否买过
                    Boolean occupy = redisTemplate.opsForValue().setIfAbsent(memberResponseVo.getId()+"-"+redisTo.getSkuId(), num.toString(), ttl, TimeUnit.MILLISECONDS);
                    //3.2 占位成功，说明该用户未秒杀过该商品，则继续
                    if (occupy){
                        //4. 校验库存和购买量是否符合要求
                        if (num <= redisTo.getSeckillLimit()) {
                            //4.1 尝试获取库存信号量
                            RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + redisTo.getRandomCode());
                            boolean acquire = semaphore.tryAcquire(num,100,TimeUnit.MILLISECONDS);
                            //4.2 获取库存成功
                            if (acquire) {
                                //5. 发送消息创建订单
                                //5.1 创建订单号
                                orderSn = IdWorker.getTimeId();
                                //5.2 创建秒杀订单to
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setMemberId(memberResponseVo.getId());
                                orderTo.setNum(num);
                                orderTo.setOrderSn(orderSn);
                                orderTo.setPromotionSessionId(redisTo.getPromotionSessionId());
                                orderTo.setSeckillPrice(redisTo.getSeckillPrice());
                                orderTo.setSkuId(redisTo.getSkuId());
                                //5.3 发送创建订单的消息
                                rabbitTemplate.convertAndSend("order-event-exchange", "order.seckill.order", orderTo);
                            }
                        }
                    }
                }
            }
            return orderSn;
        }
        return null;
    }



    private void saveSecKillSession(List<SeckillSessionWithSkusVo> sessions) {
        sessions.stream().forEach(session->{
            String key = SESSION_CACHE_PREFIX + session.getStartTime().getTime() + "_" + session.getEndTime().getTime();
            //当前活动信息未保存过
            if (!redisTemplate.hasKey(key)){
                List<String> values = session.getRelations().stream()
                        .map(sku -> sku.getPromotionSessionId() +"-"+ sku.getSkuId())
                        .collect(Collectors.toList());
                redisTemplate.opsForList().leftPushAll(key,values);
            }
        });
    }

    private void saveSecKillSku(List<SeckillSessionWithSkusVo> sessions) {
        BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
        sessions.stream().forEach(session->{
            session.getRelations().stream().forEach(sku->{
                String key = sku.getPromotionSessionId() +"-"+ sku.getSkuId();
                if (!ops.hasKey(key)){
                    SeckillSkuRedisTo redisTo = new SeckillSkuRedisTo();
                    //1. 保存SeckillSkuVo信息
                    BeanUtils.copyProperties(sku,redisTo);
                    //2. 保存开始结束时间
                    redisTo.setStartTime(session.getStartTime().getTime());
                    redisTo.setEndTime(session.getEndTime().getTime());
                    //3. 远程查询sku信息并保存
                    R r = productFeignService.info(sku.getSkuId());
                    if (r.getCode() == 0) {
                        SkuInfoVo skuInfo = r.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                        });
                        redisTo.setSkuInfoVo(skuInfo);
                    }
                    //4. 生成商品随机码，防止恶意攻击
                    String token = UUID.randomUUID().toString().replace("-", "");
                    redisTo.setRandomCode(token);
                    //5. 序列化为json并保存
                    String jsonString = JSON.toJSONString(redisTo);
                    ops.put(key,jsonString);
                    //5. 使用库存作为Redisson信号量限制库存
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    semaphore.trySetPermits(sku.getSeckillCount());
                }
            });
        });
    }
}
