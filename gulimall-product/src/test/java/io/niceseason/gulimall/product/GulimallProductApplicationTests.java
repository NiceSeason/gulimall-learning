package io.niceseason.gulimall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.niceseason.gulimall.product.dao.ProductAttrValueDao;
import io.niceseason.gulimall.product.dao.SkuSaleAttrValueDao;
import io.niceseason.gulimall.product.entity.BrandEntity;
import io.niceseason.gulimall.product.service.BrandService;
import io.niceseason.gulimall.product.vo.SkuItemSaleAttrVo;
import io.niceseason.gulimall.product.vo.SpuItemAttrGroupVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallProductApplicationTests {
    @Autowired
    private BrandService brandService;

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRedisson() {
        System.out.println(redissonClient);
    }

    @Test
    public void test() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("this is key","this is value");
        String key = ops.get("this is key");
        System.out.println(key);

    }

    @Test
    public void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setBrandId(1L);
//        brandEntity.setDescript("华为手机的描述修改");
//        brandEntity.setName("华为mate");
//        brandService.updateById(brandEntity);
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 2L));
        list.forEach((x)-> System.out.println(x));
    }

    @Test
    public void testUpload() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "######";
        String accessKeySecret = "###########";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("D:\\Works\\Note\\images\\163邮箱授权码.png");
        ossClient.putObject("gulimall-niceseason", "upload1.png", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功");
    }

    @Test
    public void testUploadByAli() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\Works\\Note\\images\\2018-02-04_123955.png");
        ossClient.putObject("gulimall-niceseason", "upload2.png", inputStream);
    }

    @Autowired
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Test
    public void testSaleAttr() {
        List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueDao.listSaleAttrs(30L);
        System.out.println(saleAttrVos);
    }

    @Autowired
    private ProductAttrValueDao productAttrValueDao;

    @Test
    public void testProductAttrValueDao(){
        List<SpuItemAttrGroupVo> productGroupAttrsBySpuId = productAttrValueDao.getProductGroupAttrsBySpuId(30L, 225L);
        System.out.println(productGroupAttrsBySpuId);
    }

    @Autowired
    private ThreadPoolExecutor executor;

    @Test
    public void testThreadPool() {
        System.out.println(executor.getCorePoolSize()+"---"+executor.getMaximumPoolSize());
    }

}
