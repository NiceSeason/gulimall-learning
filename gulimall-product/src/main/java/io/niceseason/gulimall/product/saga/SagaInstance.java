package io.niceseason.gulimall.product.saga;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SagaInstance {
    private Long id;
    private String sagaType;
    private String userId;
    private SagaStatus status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
