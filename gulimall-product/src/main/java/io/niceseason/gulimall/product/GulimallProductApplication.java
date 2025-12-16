package io.niceseason.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.PostConstruct;

@EnableRedisHttpSession
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("io.niceseason.gulimall.product.dao")
@EnableCaching
@EnableFeignClients(basePackages = {"io.niceseason.gulimall.product.feign"})
public class GulimallProductApplication {
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @PostConstruct
    public void checkOssEndpoint() {
        System.out.println("OSS endpoint = " + endpoint);
    }
    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
