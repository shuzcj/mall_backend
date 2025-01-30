package com.mall.payservice.dao;

import com.mall.common.domain.entity.Payment;

public interface PayDao {

    Integer createPayment(Payment payment);
}
