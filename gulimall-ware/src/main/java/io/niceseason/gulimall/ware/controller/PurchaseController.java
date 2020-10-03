package io.niceseason.gulimall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.niceseason.gulimall.ware.vo.MergeVo;
import io.niceseason.gulimall.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.niceseason.gulimall.ware.entity.PurchaseEntity;
import io.niceseason.gulimall.ware.service.PurchaseService;
import io.niceseason.common.utils.PageUtils;
import io.niceseason.common.utils.R;



/**
 * 采购信息
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 23:15:25
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @RequestMapping("/unreceive/list")
    public R listUnreceive(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.listUnreceive(params);
        return R.ok().put("page", page);
    }


    /**
     * 合并采购需求
     * @param mergeVo
     * @return
     */
    @PostMapping("/merge")
    public R mergePurchaseDetail(@RequestBody MergeVo mergeVo) {
        purchaseService.mergePurchaseDetail(mergeVo);
        return R.ok();
    }


    /**
     * 领取采购单
     * @param ids
     * @return
     */
    @PostMapping("/received")
    public R ReceivedPurchase(@RequestBody List<Long> ids) {
        purchaseService.ReceivedPurchase(ids);
        return R.ok();
    }

    @PostMapping("/done")
    public R finishPurchase(@RequestBody PurchaseDoneVo purchaseDoneVo) {
        purchaseService.finishPurchase(purchaseDoneVo);
        return R.ok();
    }
}
