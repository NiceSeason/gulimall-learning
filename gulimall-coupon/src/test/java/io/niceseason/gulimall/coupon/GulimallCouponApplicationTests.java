package io.niceseason.gulimall.coupon;

import io.niceseason.gulimall.coupon.entity.CouponEntity;
import io.niceseason.gulimall.coupon.service.CouponService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//@SpringBootTest
public class GulimallCouponApplicationTests {
    @Autowired
    private CouponService couponService;

    @Test
    public void contextLoads() {
//        CouponEntity couponEntity = new CouponEntity();
//        couponEntity.setCouponName("测试优惠券");
//        couponService.save(couponEntity);
//        System.out.println("保存成功");
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("测试修改版2");
        couponEntity.setId(2L);
        couponService.updateById(couponEntity);
    }

    @Test
    public void testTime() {
        LocalDate now = LocalDate.now();
        LocalTime min = LocalTime.MIN;
        LocalDateTime localDateTime = now.atTime(min);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);

        LocalDate end = now.plusDays(2);
        LocalDateTime endTime = end.atTime(LocalTime.MAX);
        System.out.println(endTime);
    }
}
