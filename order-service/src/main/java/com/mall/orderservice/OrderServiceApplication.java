package com.mall.orderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.mall.orderservice.dao")
@EnableFeignClients( basePackages = {"com.mall.api.client","com.mall.orderservice"}, defaultConfiguration = com.mall.api.config.FeignConfig.class)

public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
