package io.niceseason.gulimall.coupon.controller;

import io.niceseason.common.utils.R;
import io.niceseason.gulimall.coupon.entity.DispatchVo;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequestMapping("dispatch")
@RestController
public class DispatchController {


    @GetMapping("/test")
    public R dispatch(DispatchVo dispatchVo) {
       log.info("派工完成");
        return R.ok().put("Susscess",true);
    }
}
