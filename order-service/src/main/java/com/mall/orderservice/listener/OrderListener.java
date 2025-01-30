package com.mall.orderservice.listener;


import com.mall.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;


@Component
public class OrderListener {

    @Autowired
    private OrderService orderService;


    @RabbitListener(queues = "test.q1")
    public void listenTestQ1(String message) {
        System.out.println("q1,Received message: " + message);
    }

    @RabbitListener(queues = "test.q2")
    public void listenTestQ2(String message) {
        System.out.println("q2,Received message: " + message);
    }

    @RabbitListener(queues = "test.q3")
    public void listenTestQ3(Map<String,Object> message) {
        Set<String> keys = message.keySet();
        for (Object key : keys) {
            System.out.println("q3,Received message: " + key + " : " + message.get(key));
        }
        System.out.println("q3,Received message: " + message);
    }

//    @RabbitListener(queues = "deadLetter.queue")
//    public void deadLetterHandleMessage(String message) {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("Received in DLQ: " + now);
//        System.out.println("Received in DLQ: " + message);
//        // Further processing or logging
//    }


    @RabbitListener(queues = "deadLetter.queue")
    public void deadLetterHandleMessage(Integer orderId) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Received in DLQ time: " + now);
        System.out.println("Received in DLQ orderID: " + orderId);
        // Further processing or logging
        orderService.checkAndUpdateOrderPaymentStatus(orderId);
    }


    @RabbitListener(queues = "updateOrderStatus")
    public void updateOrderStatus(Integer orderId) {

        System.out.println("Received in updateOrderStatus==============================: " + orderId);

        orderService.updateOrderStatus(orderId);
    }



}
