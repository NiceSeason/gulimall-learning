package io.niceseason.common.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpuBoundTo {

    private Long spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;

}
