package io.niceseason.gulimall.product.saga;

public enum SagaStatus {
    RUNNING,
    COMPENSATING,
    COMPLETED,
    FAILED,
}
