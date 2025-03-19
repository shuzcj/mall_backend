package com.mall.orderservice.controller;


import com.mall.common.domain.entity.Order;
import com.mall.common.domain.entity.Vouchers;
import com.mall.common.domain.entity.VoucherOrder;
import com.mall.common.domain.vo.ApiResponse;
import com.mall.orderservice.domain.dto.VoucherOrderRequest;
import com.mall.orderservice.service.OrderService;
import com.mall.orderservice.service.VoucherOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private VoucherOrderService voucherOrderService;

    @GetMapping("/{orderId}")
    public ApiResponse<Order> getOrderById(@PathVariable Integer orderId) {
        System.out.println("Fetching order with ID: " + orderId);
        Order order = orderService.getOrderById(orderId);

        return ApiResponse.success(order);
    }


    @PostMapping("/voucherOrder")
    public ResponseEntity createVoucherOrder(@RequestBody VoucherOrderRequest voucherOrderRequest) {


        VoucherOrder voucherOrder = voucherOrderService.createVoucherOrder_redisson(voucherOrderRequest.getUserId(), voucherOrderRequest.getVoucherId());
        if(voucherOrder != null) {
            return ResponseEntity.ok().build();

        }
        return  ResponseEntity.badRequest().build();
    }

    @PostMapping("/voucherOrderLua")
    public ResponseEntity createVoucherOrderLua(@RequestBody VoucherOrderRequest voucherOrderRequest) {


        int r = voucherOrderService.createVoucherOrder_lua(voucherOrderRequest.getUserId(), voucherOrderRequest.getVoucherId());
        if(r==0) {
            return ResponseEntity.ok().build();

        }
        return  ResponseEntity.badRequest().build();
    }
}
//模拟1000个用户总共1w次请求抢同一类型优惠券500张，一人一单