package com.mall.orderservice.service;


import com.mall.common.domain.dto.StockUpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("UpdateStock")
    public StockUpdateResponse updateStock(@RequestParam Integer productId, @RequestParam Integer stock);

}
