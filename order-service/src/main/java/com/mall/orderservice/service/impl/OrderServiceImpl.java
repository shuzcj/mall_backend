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

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer createOrder(Integer userId, List<SimpleOrderItem> simpleOrderItems) {

        //quantity is negative
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems= new ArrayList<OrderItem>();
        for (SimpleOrderItem simpleOrderItem : simpleOrderItems) {
            StockUpdateResponse stockUpdateResponse  = productClient.updateStock(simpleOrderItem.getId(), simpleOrderItem.getQuantity());

            if(stockUpdateResponse.isSuccess()) {

                BigDecimal price =stockUpdateResponse.getValue().multiply(BigDecimal.valueOf(-1L *simpleOrderItem.getQuantity()));
                totalPrice = totalPrice.add(price);
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(simpleOrderItem.getId());
                orderItem.setQuantity(-1*simpleOrderItem.getQuantity());
                orderItem.setPricePerUnit(stockUpdateResponse.getValue());
                orderItem.setSubtotal(price);

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

        System.out.println("Order created: " + order);

        String exchangeName = "dlx.exchange";
        String routingKey = "delay";  // Use 'delay' to send the message to the delayQueue
        Integer orderId = order.getId();
        now = LocalDateTime.now();
        System.out.println("Sending message: " + now);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);


        return 0;
    }

    @Override
    public void checkAndUpdateOrderPaymentStatus(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        if (order != null && "pending payment".equals(order.getPaymentStatus())) {
            order.setOrderStatus("closed");
            order.setPaymentStatus("unpaid");
            order.setUpdatedAt(LocalDateTime.now());
            orderDao.updateOrderStatus(order);
        } else if (order != null && "paid".equals(order.getPaymentStatus())) {
            // If already paid, do nothing.
            System.out.println("Order already paid. No action needed.");
        }
    }


}
