package com.mall.userservice.dao;

import com.mall.common.domain.entity.User;


public interface UserDao {

    User getFirstUser();

    User getUserByUserName(String username);

    void registerUser(User user);

    int checkUsernameExists(String username);


}