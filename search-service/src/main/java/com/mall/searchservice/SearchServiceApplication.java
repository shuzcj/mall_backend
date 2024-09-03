package com.mall.searchservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.mall.api.client", defaultConfiguration = com.mall.api.config.FeignConfig.class)
@SpringBootApplication(scanBasePackages= {"com.mall.common.utils", "com.mall.searchservice"})
@MapperScan("com.mall.searchservice.dao")
public class SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

}
