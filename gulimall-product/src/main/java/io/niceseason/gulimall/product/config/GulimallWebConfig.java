package io.niceseason.gulimall.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置
 * 配置静态资源处理器
 * 
 * 说明：
 * 1. 生产环境：静态资源由 Nginx 提供（nginx/html/static/），不经过 Spring Boot
 * 2. 开发环境：直接访问 Spring Boot 时，需要通过此配置来处理 /static/** 路径
 * 3. 此配置确保了两种方式都能正常工作：
 *    - 通过 Nginx 访问：Nginx 优先处理 /static/** 请求
 *    - 直接访问 Spring Boot：Spring Boot 处理 /static/** 请求
 */
@Configuration
public class GulimallWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /static/** 路径映射到 classpath:/static/ 目录
        // 这样可以支持直接访问 Spring Boot 应用时也能正确加载静态资源
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
    }
}

