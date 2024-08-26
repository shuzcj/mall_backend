package com.mall.userservice.service.impl;

import com.mall.common.exception.UserAlreadyExistsException;
import com.mall.common.exception.LoginFailedException;
import com.mall.common.exception.UserNotLoggedInException;
import com.mall.common.utils.JwtUtil;
import com.mall.common.utils.RedisCache;
import com.mall.userservice.dao.UserDao;
import com.mall.userservice.domain.dto.LoginRequest;
import com.mall.userservice.domain.dto.RegisterRequest;
import com.mall.userservice.domain.entity.User;
import com.mall.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String login(LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        // 查询用户信息
        User user = userDao.getUserByUserName(loginRequest.getUsername());
        System.out.println(user);
        // 如果用户不存在，抛出异常
        if (user == null) {
            throw new LoginFailedException("用户名或密码错误");
        }

        // 验证密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassWord());
        if (!isPasswordMatch) {
            throw new LoginFailedException("用户名或密码错误");
        }

        // 登录成功，生成JWT
        String jwt = JwtUtil.createJWT(user.getUserName());
        String userKey = "user:" + user.getUserName();
        redisCache.setCacheObject(userKey, user, 1, TimeUnit.HOURS);
        return jwt;
    }

    @Override
    public String logout(String username) {
        // 在 Redis 中查找用户信息
        String userKey = "user:" + username;
        User user = redisCache.getCacheObject(userKey);

        // 如果用户未登录，抛出异常
        if (user == null) {
            throw new UserNotLoggedInException("用户未登录");
        }

        // 删除 Redis 中的用户信息
        redisCache.deleteObject(userKey);

        // 返回成功消息
        return "登出成功";
    }



    @Override
    public String register(RegisterRequest registerRequest) {
        // 检查用户名是否已经存在
        if (checkUsernameExists(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("用户名已存在");
        }

        // 使用 BCrypt 进行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // 创建并设置用户实体
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassWord(encodedPassword);
        user.setType(registerRequest.getType()); // 如果有用户类型字段

        // 保存用户信息到数据库
        userDao.registerUser(user);

        // 返回成功消息
        return "注册成功";
    }


    @Override
    public Boolean checkUsernameExists(String username) {

        System.out.println("username = " + username);
        int exists = userDao.checkUsernameExists(username);
        if (exists > 0) {
            return true;
        }

        return false;
    }

}
