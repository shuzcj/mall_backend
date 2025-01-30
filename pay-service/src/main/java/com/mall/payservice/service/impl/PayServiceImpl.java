package com.mall.payservice.service.impl;

import com.mall.common.domain.entity.Payment;
import com.mall.payservice.dao.PayDao;
import com.mall.payservice.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {


    @Autowired
    private PayDao payDao;

    @Override
    public Integer createPayment(Payment payment) {

        System.out.println("Creating payment: "+payment);

        return payDao.createPayment(payment);
    }
}
