package io.niceseason.gulimall.product.saga.impl;

import io.niceseason.gulimall.product.saga.SagaInstance;
import io.niceseason.gulimall.product.saga.SagaInstanceRepository;
import io.niceseason.gulimall.product.saga.SagaStatus;
import org.springframework.stereotype.Service;


@Service
public class SagaInstacnceRepositoryImpl implements SagaInstanceRepository {
    @Override
    public SagaInstance create(String sagaType, String businessKey) {
        return null;
    }

    @Override
    public SagaInstance findById(Long sagaId) {
        return null;
    }

    @Override
    public void updateStatus(Long sagaId, SagaStatus status) {

    }
}
