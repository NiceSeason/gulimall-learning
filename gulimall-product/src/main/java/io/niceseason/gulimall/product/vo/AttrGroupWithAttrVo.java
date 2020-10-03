package io.niceseason.gulimall.product.vo;

import io.niceseason.gulimall.product.entity.AttrEntity;
import io.niceseason.gulimall.product.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrVo extends AttrGroupEntity {
    private List<AttrEntity> attrs;
}
