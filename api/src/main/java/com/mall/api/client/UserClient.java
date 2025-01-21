package com.mall.api.client;

import com.mall.api.config.FeignConfig;
import com.mall.common.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "user-service",configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{userId}")
    User getUserById(@PathVariable("userId") Integer userId);
}
