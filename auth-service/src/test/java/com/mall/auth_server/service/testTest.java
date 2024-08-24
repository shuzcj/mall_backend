package com.mall.auth_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mall.auth_server.service.impl.test;


@SpringBootTest
public class testTest {

    @Autowired
    private test test;

    @Test
    public void test() {
        test.test();

    }
}
