package com.mall.api.client;

import com.mall.api.config.FeignConfig;
import com.mall.common.domain.entity.User;
import com.mall.common.domain.vo.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name= "user-service",configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{userId}")
    ApiResponse<User> getUserById(@PathVariable("userId") Integer userId);

    @PostMapping("/user/deductBalance")
    ApiResponse<Boolean> deductBalance(@RequestParam("userId") Integer userId,@RequestParam("amount") BigDecimal amount);
}
