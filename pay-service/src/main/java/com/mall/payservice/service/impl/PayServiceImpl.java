package com.mall.payservice.service.impl;

import com.mall.api.client.OrderClient;
import com.mall.api.client.UserClient;
import com.mall.common.domain.entity.Order;
import com.mall.common.domain.entity.Payment;
import com.mall.common.domain.entity.User;
import com.mall.payservice.dao.PayDao;
import com.mall.payservice.service.PayService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PayDao payDao;

    @Override
    public Integer createPayment(Payment payment) {

        System.out.println("Creating payment: "+payment);

        return payDao.createPayment(payment);
    }

    @Override
    public String processPayment(Integer orderId, Integer userId) {

        Payment payment = payDao.getPaymentByOrderId(orderId);

        if(payment==null){
            return "Payment not found";
        }

        if(!Objects.equals(payment.getUserId(), userId)){
            return "404";
        }

        Order order= orderClient.getOrderById(orderId).getData();
        if(order.getOrderStatus().equals("closed")){
            return "Payment timeout";
        }

        Boolean result = userClient.deductBalance(userId, payment.getAmount()).getData();
        if(!result){
            return "Balance not enough";
        }
        LocalDateTime now = LocalDateTime.now();
        payment.setStatus("paid");
        payment.setUpdatedAt(now);
        payment.setPaymentDate(now);
        payDao.updatePaymentStatus(payment);

        String exchangeName = "order";

        String routingKey = "status";

        rabbitTemplate.convertAndSend(exchangeName, routingKey,orderId);



        return "Payment success";
    }

    @Override
    public Payment getPaymentByOrderId(Integer paymentId) {

        return payDao.getPaymentByOrderId(paymentId);
    }


}
