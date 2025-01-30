package com.mall.userservice.dao;

import com.mall.common.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;


public interface UserDao {

    User getFirstUser();

    User getUserByUserName(String username);

    void registerUser(User user);

    int checkUsernameExists(String username);

    User getUserByUserId(Long id);

    int deductBalance(@Param("userId") Integer userId,@Param("amount") BigDecimal amount);

}