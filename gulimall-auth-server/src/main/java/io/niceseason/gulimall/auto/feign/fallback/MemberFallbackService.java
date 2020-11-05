package io.niceseason.gulimall.auto.feign.fallback;

import io.niceseason.common.exception.BizCodeEnum;
import io.niceseason.common.utils.R;
import io.niceseason.gulimall.auto.feign.MemberFeignService;
import io.niceseason.gulimall.auto.vo.SocialUser;
import io.niceseason.gulimall.auto.vo.UserLoginVo;
import io.niceseason.gulimall.auto.vo.UserRegisterVo;
import org.springframework.stereotype.Service;

@Service
public class MemberFallbackService implements MemberFeignService {
    @Override
    public R register(UserRegisterVo registerVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(UserLoginVo loginVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(SocialUser socialUser) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }
}
