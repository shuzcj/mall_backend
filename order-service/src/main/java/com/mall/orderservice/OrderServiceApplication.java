package com.mall.orderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.mall.orderservice","com.mall.common"})
@MapperScan("com.mall.orderservice.dao")
@EnableFeignClients( basePackages = {"com.mall.api.client","com.mall.orderservice"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
