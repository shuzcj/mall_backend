package com.mall.userservice.controller;

import com.mall.userservice.domain.dto.LoginRequest;
import com.mall.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mall.common.domain.vo.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {

        String jwt = userService.login(loginRequest);
        return ApiResponse.success(jwt);
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody LoginRequest loginRequest) {


        //String jwt = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return null;
    }

    @GetMapping("/hello")
    public ApiResponse<String> hello() {

        return ApiResponse.success("hello,this is user-service");
    }

    @GetMapping("/usernameExists")
    public ApiResponse<Boolean> usernameExists(@RequestParam String username) {

        return null;
    }


}
