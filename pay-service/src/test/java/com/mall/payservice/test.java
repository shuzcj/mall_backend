package com.mall.payservice;


import com.mall.common.domain.entity.Payment;
import com.mall.payservice.service.PayService;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class test {

    @Autowired
    private PayService payService;

    @Test
    public void test(){

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(100));
        payment.setOrderId(5);
        payment.setUserId(1);
        LocalDateTime now = LocalDateTime.now();
        payment.setCreatedAt(now);
        payment.setUpdatedAt(now);
        payment.setPaymentDate(null);
        payment.setStatus("pending payment");

        Integer result = payService.createPayment(payment);
        System.out.println(result);

    }


    @Test
    public void processTest(){
        payService.processPayment(19,8);
    }

}
