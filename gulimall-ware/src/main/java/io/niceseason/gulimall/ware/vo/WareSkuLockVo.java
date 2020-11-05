package io.niceseason.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

@Data
public class WareSkuLockVo {
    private String OrderSn;

    private List<OrderItemVo> locks;
}
