package com.mall.mall_backend.service.impl;

import com.mall.mall_backend.domain.entity.User;
import com.mall.mall_backend.domain.sercurity.LoginUser;
import com.mall.mall_backend.exception.LoginFailedException;
import com.mall.mall_backend.service.LogInService;
import com.mall.mall_backend.utils.JwtUtil;
import com.mall.mall_backend.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mall.mall_backend.dao.UserLogInDao;


import javax.security.auth.login.CredentialException;
import java.util.Objects;

@Service
public class LogInServiceImpl implements LogInService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserLogInDao userLogInDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(User user) {
        // Create the authentication token with the user's credentials
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassWord());

        // AuthenticationManager handles the authentication process
        /*
        When you use @Autowired on an interface like AuthenticationManager, Spring's dependency injection mechanism automatically injects a concrete implementation of that interface.
        This is possible because Spring Security provides a default implementation of AuthenticationManager behind the scenes.
         */
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);



        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new LoginFailedException(LoginFailedException.Reason.WRONG_PASSWORD);
        }

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        System.out.println("登陆成功"+loginUser.getUser());
        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userid);

        redisCache.setCacheObject("login:"+userid,loginUser);

        //TODO:redis设置过期时间

        return jwt;

    }

    @Override
    public void register(User user) {
        // 检查用户名是否已经存在
        User existingUser = userLogInDao.getUserByUserName(user.getUserName());
        if (existingUser != null) {
            throw new LoginFailedException(LoginFailedException.Reason.USERNAME_ALREADY_EXISTS);
        }

        // 加密密码
        String encodedPassword = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(encodedPassword);

        // 保存用户信息到数据库
        userLogInDao.registerUser(user);


    }
}
