package io.niceseason.gulimall.ware;

import io.niceseason.gulimall.ware.entity.WareInfoEntity;
import io.niceseason.gulimall.ware.service.WareInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallWareApplicationTests {
    @Autowired
    private WareInfoService wareInfoService;

    @Test
    void contextLoads() {
        WareInfoEntity wareInfoEntity = new WareInfoEntity();
        wareInfoEntity.setName("测试信息");
        wareInfoService.save(wareInfoEntity);
    }

}
