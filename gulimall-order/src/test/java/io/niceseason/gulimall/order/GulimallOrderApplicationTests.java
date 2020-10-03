package io.niceseason.gulimall.order;

import io.niceseason.gulimall.order.entity.OrderEntity;
import io.niceseason.gulimall.order.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallOrderApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        OrderEntity entity = new OrderEntity();
        entity.setNote("这是一条测试信息");
        orderService.save(entity);
    }

}
