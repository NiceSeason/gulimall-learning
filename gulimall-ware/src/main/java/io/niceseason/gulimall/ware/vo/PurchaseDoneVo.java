package io.niceseason.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * {
 *    id: 123,//采购单id
 *    items: [{itemId:1,status:4,reason:""}]//完成/失败的需求详情
 * }
 */
@Data
public class PurchaseDoneVo {
    private Long id;
    private List<PurchaseDoneItemVo> items;
}
