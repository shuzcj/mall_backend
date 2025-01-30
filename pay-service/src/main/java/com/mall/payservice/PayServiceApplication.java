package com.mall.payservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.mall.payservice","com.mall.common"})//scan common package to get MqConfig
@MapperScan("com.mall.payservice.dao")
@EnableFeignClients(basePackages = "com.mall.api.client", defaultConfiguration = com.mall.api.config.FeignConfig.class)
public class PayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }

}
