package com.mall.orderservice.service;

import com.mall.common.domain.entity.Order;
import com.mall.orderservice.domain.SimpleOrderItem;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, List<SimpleOrderItem> simpleOrderItems);

    void checkAndUpdateOrderPaymentStatus(Integer orderId);

    Order getOrderById(Integer orderId);

    void updateOrderStatus(Integer orderId);

}
