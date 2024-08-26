package com.mall.userservice.service;


import com.mall.userservice.domain.dto.LoginRequest;
import com.mall.userservice.domain.dto.RegisterRequest;

public interface UserService  {


    public String login(LoginRequest loginRequest);
    public String register(RegisterRequest registerRequest);
    public Boolean checkUsernameExists(String username);
    public String logout(String username);
}
