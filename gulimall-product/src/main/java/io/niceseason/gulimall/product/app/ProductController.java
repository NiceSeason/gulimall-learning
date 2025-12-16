package io.niceseason.gulimall.product.app;


import io.niceseason.common.utils.R;
import io.niceseason.gulimall.product.entity.SkuInfoEntity;
import io.niceseason.gulimall.product.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product/product")
public class ProductController {

    @Autowired
    private CouponFeignService feignService ;

    @GetMapping("coupons")
    public R test(){
        SkuInfoEntity entity = new SkuInfoEntity();
        entity.setBrandId(1111111111L);
        R memberCoupons = feignService.memberCoupons();
        return R.ok().put("SkuInfo",entity).put("Coupons",memberCoupons.get("coupons"));
    }
}
