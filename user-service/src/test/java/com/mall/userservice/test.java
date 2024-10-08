package com.mall.userservice;


import com.mall.userservice.domain.dto.RegisterRequest;
import com.mall.userservice.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class test {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveAndRetrievePerson() {
        RegisterRequest registerRequest = new RegisterRequest("6", "6", '2');

        userService.register(registerRequest);
    }


}