package io.niceseason.gulimall.coupon;

import io.niceseason.gulimall.coupon.entity.CouponEntity;
import io.niceseason.gulimall.coupon.service.CouponService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallCouponApplicationTests {
    @Autowired
    private CouponService couponService;

    @Test
    void contextLoads() {
//        CouponEntity couponEntity = new CouponEntity();
//        couponEntity.setCouponName("测试优惠券");
//        couponService.save(couponEntity);
//        System.out.println("保存成功");
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("测试修改版2");
        couponEntity.setId(2L);
        couponService.updateById(couponEntity);
    }

}
