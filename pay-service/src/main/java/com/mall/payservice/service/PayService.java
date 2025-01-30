package com.mall.payservice.service;

import com.mall.common.domain.entity.Payment;

public interface PayService {

    public Integer createPayment(Payment payment);

    String processPayment(Integer orderId, Integer userId);

    Payment getPaymentByOrderId(Integer paymentId);

}
