package com.mall.mall_backend.dao;

import com.mall.mall_backend.domain.entity.User;


public interface UserLogInDao {

    User getFirstUser();

    User getUserByUserName(String username);

    void registerUser(User user);
}