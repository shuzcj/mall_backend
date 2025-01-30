package com.mall.userservice.service;


import com.mall.common.domain.entity.User;
import com.mall.userservice.domain.dto.LoginRequest;
import com.mall.userservice.domain.dto.RegisterRequest;

import java.math.BigDecimal;

public interface UserService  {


    public String login(LoginRequest loginRequest);
    public String register(RegisterRequest registerRequest);
    public Boolean checkUsernameExists(String username);
    public String logout(String username);
    public User getUserInfoByUserId(Long id);

    Boolean deductBalance(Integer userId, BigDecimal amount);
}
