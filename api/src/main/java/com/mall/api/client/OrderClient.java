package com.mall.api.client;


import com.mall.common.domain.entity.Order;
import com.mall.common.domain.vo.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")//microservice name
public interface OrderClient {

    @GetMapping("/order/{orderId}")
    ApiResponse<Order> getOrderById(@PathVariable("orderId") Integer orderId);

}
