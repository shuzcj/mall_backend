package com.mall.orderservice;

import com.mall.orderservice.service.VoucherOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class RedisTest {

    @Autowired
    private VoucherOrderService voucherOrderService;

    @Test
    public void test() {
        voucherOrderService.insertVoucherInRedis(1,500);
    }

    @Test
    public void test1() {
        voucherOrderService.createVoucherOrder_lua(1,1);
    }

}
