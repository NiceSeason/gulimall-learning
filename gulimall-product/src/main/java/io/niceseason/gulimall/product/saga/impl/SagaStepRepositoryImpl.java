package io.niceseason.gulimall.product.saga.impl;

import io.niceseason.gulimall.product.saga.SagaStep;
import io.niceseason.gulimall.product.saga.SagaStepRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SagaStepRepositoryImpl implements SagaStepRepository {
    @Override
    public SagaStep createStep(Long sagaId, String stepName) {
        return null;
    }

    @Override
    public void markSuccess(Long stepId) {

    }

    @Override
    public void markCompensated(Long stepId) {

    }

    @Override
    public List<SagaStep> findUnCompensatedSteps(Long sagaId) {
        return Collections.emptyList();
    }
}
