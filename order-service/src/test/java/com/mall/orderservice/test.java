package com.mall.orderservice;


import com.mall.orderservice.domain.SimpleOrderItem;
import com.mall.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
public class test {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderService orderService;

    @Test
    void asd() {

        Integer userId = 8;
        List<SimpleOrderItem> simpleOrderItems = new ArrayList<>();
        SimpleOrderItem item = new SimpleOrderItem();
        item.setId(55);
        item.setQuantity(-11);
        simpleOrderItems.add(item);
        orderService.createOrder(userId, simpleOrderItems);
    }

    @Test
    void MQtest() {
        System.out.println("Hello World");
        // 队列名称
        String queueName = "test.q1";
        // 消息
        String message = "hello, spring amqp!";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    void MQFanoutTest(){

        // 交换机名称
        String exchangeName = "test.fanout";
        // 消息
        String message = "hello, spring amqp!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }


    @Test
    void MQDirectTest(){

        // 交换机名称
        String exchangeName = "test.direct";
        // 路由键
        String routingKey = "blue";
        // 消息
        String message = "test direct blue";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    @Test
    void MQTopicTest(){

        // 交换机名称
        String exchangeName = "test.topic";
        // 路由键
        String routingKey = "asd.q2";
        // 消息
        String message = "test topic "+routingKey;
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    @Test
    void MQSendMsg(){
        Map<String, Object> map = new HashMap<>();

        map.put("name", "zhangsan");
        map.put("age", 18);

        rabbitTemplate.convertAndSend("test.direct1", "red", map);

    }


    @Test
    void MQTestDeadLetter() {
        // 交换机名称
        String exchangeName = "dlx.exchange";
        String routingKey = "delay";  // Use 'delay' to send the message to the delayQueue
        String message = "Test message for handling with TTL";
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Sending message: " + now);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }



}
