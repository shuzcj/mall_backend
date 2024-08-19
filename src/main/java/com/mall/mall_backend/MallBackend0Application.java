package com.mall.mall_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mall.mall_backend.controller.test;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class MallBackend0Application {

    @Autowired
    private test test;

    public static void main(String[] args) {
        SpringApplication.run(MallBackend0Application.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("asdasdasd");
        test.sayHello();
    }
}

