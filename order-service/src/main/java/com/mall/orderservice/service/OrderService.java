package com.mall.orderservice.service;

import com.mall.orderservice.domain.SimpleOrderItem;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, List<SimpleOrderItem> simpleOrderItems);
}
