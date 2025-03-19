package com.mall.orderservice.service;

import com.mall.common.domain.entity.VoucherOrder;

public interface VoucherOrderService {


    VoucherOrder createVoucherOrder1(int userId, int voucherId);

    VoucherOrder createVoucherOrder_redisson(int userId, int voucherId);


    int createVoucherOrder_lua(int userId, int voucherId);

    void insertVoucherInRedis(int voucherId, int stock);
}
