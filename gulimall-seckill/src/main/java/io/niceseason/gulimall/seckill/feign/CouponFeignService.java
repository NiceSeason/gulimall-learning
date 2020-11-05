package io.niceseason.gulimall.seckill.feign;

import io.niceseason.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "gulimall-coupon")
public interface CouponFeignService {
    @RequestMapping("coupon/seckillsession/getSeckillSessionsIn3Days")
    R getSeckillSessionsIn3Days();
}
