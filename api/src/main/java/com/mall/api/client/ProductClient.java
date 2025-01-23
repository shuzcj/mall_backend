package com.mall.api.client;

import com.mall.common.domain.dto.StockUpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")//microservice name
public interface ProductClient {
    @PostMapping("/product/updateStock")//full path
    StockUpdateResponse updateStock(@RequestParam("productId") Integer productId, @RequestParam("stock") Integer stock);
}

