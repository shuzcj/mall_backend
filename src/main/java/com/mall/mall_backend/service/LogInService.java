package com.mall.mall_backend.service;


import com.mall.mall_backend.domain.entity.User;

public interface LogInService {


    public String login(User user);

    public void register(User user);


}
