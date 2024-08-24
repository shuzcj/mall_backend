package com.mall.api.client;

import com.mall.api.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "search-service",configuration = FeignConfig.class)
public interface searchClient {

    @GetMapping("/search/hello")
   String test();
}
