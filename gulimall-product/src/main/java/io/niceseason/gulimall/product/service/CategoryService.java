package io.niceseason.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.niceseason.common.utils.PageUtils;
import io.niceseason.gulimall.product.entity.CategoryEntity;
import io.niceseason.gulimall.product.vo.Catalog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:36
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);

    /**
     * 找到该三级分类的完整路径
     * @param categorygId
     * @return
     */
    Long[] findCatelogPathById(Long categorygId);

    void updateCascade(CategoryEntity category);

    List<CategoryEntity> getLevel1Catagories();

    Map<String, List<Catalog2Vo>> getCategoryMap();

    Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisLock();

    Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisson();

    Map<String, List<Catalog2Vo>> getCatalogJsonDbWithSpringCache();
}

