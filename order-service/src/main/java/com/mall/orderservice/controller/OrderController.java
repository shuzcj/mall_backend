package com.mall.orderservice.controller;


import com.mall.common.domain.entity.Order;
import com.mall.common.domain.vo.ApiResponse;
import com.mall.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ApiResponse<Order> getOrderById(@PathVariable Integer orderId) {
        System.out.println("Fetching order with ID: " + orderId);
        Order order = orderService.getOrderById(orderId);

        return ApiResponse.success(order);
    }



}
