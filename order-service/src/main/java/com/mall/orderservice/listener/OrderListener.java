package com.mall.orderservice.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;


@Component
public class OrderListener {


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

    @RabbitListener(queues = "deadLetter.queue")
    public void deadLetterHandleMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Received in DLQ: " + now);
        System.out.println("Received in DLQ: " + message);
        // Further processing or logging
    }


}
