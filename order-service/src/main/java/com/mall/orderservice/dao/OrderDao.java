package com.mall.orderservice.dao;

import com.mall.common.domain.entity.Order;
import com.mall.common.domain.entity.OrderItem;
import com.mall.common.domain.entity.VoucherOrder;
import com.mall.orderservice.domain.SimpleOrderItem;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderDao {

    void insertOrder(Order order);
    void insertOrderItem(OrderItem orderItem);

    Order getOrderById(Integer id);

    void updateOrderStatus(Order order);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);


    int decrementVoucherStock(Integer voucherId);

    int getVoucherStock(Integer voucherId);

    void insertVoucherOrder(VoucherOrder voucherOrder);

    int selectVoucherOrderCountByUserIdAndVoucherId(@Param("userId") Integer userId, @Param("voucherId") Integer voucherId);

}
