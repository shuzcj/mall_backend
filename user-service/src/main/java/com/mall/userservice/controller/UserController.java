package com.mall.userservice.controller;

import com.mall.userservice.domain.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mall.common.vo.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class UserController {



    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {

        //String jwt = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return null;
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody LoginRequest loginRequest) {


        //String jwt = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return null;
    }

    @GetMapping("/usernameExists")
    public ApiResponse<Boolean> usernameExists(@RequestParam String username) {

        return null;
    }


}
