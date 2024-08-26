package com.mall.userservice.service.impl;

import com.mall.userservice.dao.UserDao;
import com.mall.userservice.domain.dto.LoginRequest;
import com.mall.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public String login(LoginRequest loginRequest) {

        System.out.println("loginRequest = " + loginRequest);



        return "login";
    }


    @Override
    public String register(LoginRequest loginRequest) {

        System.out.println("loginRequest = " + loginRequest);



        return "register";
    }

    @Override
    public Boolean checkUsernameExists(String username) {

        System.out.println("username = " + username);



        return false;
    }

}
