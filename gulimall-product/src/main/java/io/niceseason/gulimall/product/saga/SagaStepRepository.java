package io.niceseason.gulimall.product.saga;

import org.springframework.stereotype.Component;

import java.util.List;


public interface SagaStepRepository {
    SagaStep createStep(Long sagaId, String stepName);

    void markSuccess(Long stepId);

    void markCompensated(Long stepId);

    List<SagaStep> findUnCompensatedSteps(Long sagaId);
}
