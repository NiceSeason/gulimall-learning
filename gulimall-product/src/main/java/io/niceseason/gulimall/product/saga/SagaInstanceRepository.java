package io.niceseason.gulimall.product.saga;


import org.springframework.stereotype.Component;

public interface SagaInstanceRepository {
    SagaInstance create(String sagaType, String businessKey);

    SagaInstance findById(Long sagaId);

    void updateStatus(Long sagaId, SagaStatus status);
}
