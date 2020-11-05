package io.niceseason.gulimall.auto.feign;

import io.niceseason.common.utils.R;
import io.niceseason.gulimall.auto.feign.fallback.MemberFallbackService;
import io.niceseason.gulimall.auto.vo.SocialUser;
import io.niceseason.gulimall.auto.vo.UserLoginVo;
import io.niceseason.gulimall.auto.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "gulimall-member",fallback = MemberFallbackService.class)
public interface MemberFeignService {

    @RequestMapping("member/member/register")
    R register(@RequestBody UserRegisterVo registerVo);


    @RequestMapping("member/member/login")
     R login(@RequestBody UserLoginVo loginVo);

    @RequestMapping("member/member/oauth2/login")
    R login(@RequestBody SocialUser socialUser);
}
