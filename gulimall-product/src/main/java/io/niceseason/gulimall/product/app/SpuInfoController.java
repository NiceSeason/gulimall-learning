package io.niceseason.gulimall.product.app;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.niceseason.gulimall.product.entity.SkuInfoEntity;
import io.niceseason.gulimall.product.vo.SpuSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.niceseason.gulimall.product.entity.SpuInfoEntity;
import io.niceseason.gulimall.product.service.SpuInfoService;
import io.niceseason.common.utils.PageUtils;
import io.niceseason.common.utils.R;



/**
 * spu信息
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:36
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;


    @RequestMapping("/skuId/{skuId}")
    public R getSpuBySkuId(@PathVariable("skuId") Long skuId) {
        SpuInfoEntity spuInfoEntity = spuInfoService.getSpuBySkuId(skuId);
        return R.ok().setData(spuInfoEntity);
    }


    /**
     * 商品上架
     * @return
     */
    @PostMapping("/{spuId}/up")
    public R upSpuForSearch(@PathVariable("spuId")Long spuId) {
        spuInfoService.upSpuForSearch(spuId);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
//    @RequestMapping("/save")
//    public R save(@RequestBody SpuInfoEntity spuInfo){
//		spuInfoService.save(spuInfo);
//
//        return R.ok();
//    }

    @RequestMapping("/save")
    public R save(@RequestBody SpuSaveVo spuSaveVo){
        spuInfoService.saveSpuSaveVo(spuSaveVo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
