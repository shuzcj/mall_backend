package com.mall.productservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.mall.productservice","com.mall.common"})
@MapperScan("com.mall.productservice.dao")
@EnableFeignClients(basePackages = "com.mall.api.client", defaultConfiguration = com.mall.api.config.FeignConfig.class)
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
