package com.mall.payservice.controller;


import com.mall.common.domain.entity.Payment;
import com.mall.common.domain.vo.ApiResponse;
import com.mall.payservice.service.PayService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping
    public Integer createPayment(@RequestBody Payment payment){

        System.out.println("paycontroller:"+payment);

        Integer paymentId = payService.createPayment(payment);

        return paymentId;
    }

    @PostMapping("/process")
    public ApiResponse<String> processPayment(
            @RequestHeader(value = "user-info", required = false) Long userId,
            @RequestBody Integer orderId){




        return ApiResponse.success("Payment processed successfully");
    }


}
