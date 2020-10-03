package io.niceseason.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 *   purchaseId: 1, //整单id
 *     items:[1,2,3,4] //合并项集合
 */
@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}
