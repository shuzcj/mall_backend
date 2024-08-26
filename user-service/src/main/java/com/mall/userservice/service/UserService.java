package com.mall.userservice.service;


import com.mall.userservice.domain.dto.LoginRequest;

public interface UserService  {


    public String login(LoginRequest loginRequest);
    public String register(LoginRequest loginRequest);
    public Boolean checkUsernameExists(String username);

}
