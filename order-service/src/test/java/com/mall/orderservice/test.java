package com.mall.orderservice;


import com.mall.orderservice.domain.SimpleOrderItem;
import com.mall.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class test {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Autowired
    private OrderService orderService;

    @Test
    void asd() {

        Integer userId = 1;
        List<SimpleOrderItem> simpleOrderItems = new ArrayList<>();
        SimpleOrderItem item = new SimpleOrderItem();
        item.setId(55);
        item.setQuantity(-2);
        simpleOrderItems.add(item);
        orderService.createOrder(userId, simpleOrderItems);
    }
}
