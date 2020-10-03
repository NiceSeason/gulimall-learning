package io.niceseason.gulimall.cart.to;

import lombok.Data;

@Data
public class UserInfoTo {

    private Long userId;

    private String userKey;

    /**
     * 浏览器是否已有user-key
     */
    private Boolean tempUser = false;

}
