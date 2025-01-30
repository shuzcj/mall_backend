package com.mall.orderservice.dao;

import com.mall.common.domain.entity.Order;
import com.mall.common.domain.entity.OrderItem;
import com.mall.orderservice.domain.SimpleOrderItem;

import java.util.List;

public interface OrderDao {

    void insertOrder(Order order);
    void insertOrderItem(OrderItem orderItem);

    Order getOrderById(Integer id);

    void updateOrderStatus(Order order);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
