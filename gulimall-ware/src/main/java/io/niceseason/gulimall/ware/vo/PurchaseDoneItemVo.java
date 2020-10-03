package io.niceseason.gulimall.ware.vo;

import lombok.Data;

/**
 *   items: [{itemId:1,status:4,reason:""}]//完成/失败的需求详情
 */
@Data
public class PurchaseDoneItemVo {
    private Long itemId;
    private Integer status;
    private String reason;
}
