package com.mall.orderservice.service.impl;

import com.mall.api.client.ProductClient;
import com.mall.api.client.UserClient;
import com.mall.common.domain.dto.StockUpdateResponse;
import com.mall.common.domain.entity.Order;
import com.mall.common.domain.entity.OrderItem;
import com.mall.common.domain.entity.User;
import com.mall.orderservice.dao.OrderDao;
import com.mall.orderservice.domain.SimpleOrderItem;
import com.mall.orderservice.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer createOrder(Integer userId, List<SimpleOrderItem> simpleOrderItems) {

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems= null;
        for (SimpleOrderItem simpleOrderItem : simpleOrderItems) {
            StockUpdateResponse stockUpdateResponse  = productClient.updateStock(simpleOrderItem.getId(), simpleOrderItem.getQuantity());
            if(stockUpdateResponse.isSuccess()) {
                totalPrice = totalPrice.add(stockUpdateResponse.getValue().multiply(BigDecimal.valueOf(simpleOrderItem.getQuantity())));

                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(simpleOrderItem.getId());
                orderItem.setQuantity(simpleOrderItem.getQuantity());
                orderItem.setPricePerUnit(stockUpdateResponse.getValue());
                orderItem.setSubtotal(totalPrice);

                orderItems.add(orderItem);

            }
            else{
                return 0;
            }
        }
        User user = userClient.getUserById(userId);

        Order  order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalPrice);
        order.setOrderStatus("pending payment");
        order.setPaymentStatus("pending payment");
        order.setUserAddress(user.getAddress());
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);

        orderDao.insertOrder(order);

        for(OrderItem orderItem: orderItems){
            orderItem.setOrderId(order.getId());
            orderDao.insertOrderItem(orderItem);
        }

        return 0;
    }
}
