package io.niceseason.gulimall.product.saga;


import lombok.Data;

@Data
public class SagaStep {
    private Long id;
    private Long sagaId;
    private String stepName;
    private boolean success;
    private boolean compensated;

}
