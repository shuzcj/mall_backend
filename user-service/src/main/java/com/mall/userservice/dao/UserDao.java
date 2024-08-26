package com.mall.userservice.dao;

import com.mall.userservice.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserDao {

    User getFirstUser();

    User getUserByUserName(String username);

    void registerUser(User user);

    int checkUsernameExists(String username);


}