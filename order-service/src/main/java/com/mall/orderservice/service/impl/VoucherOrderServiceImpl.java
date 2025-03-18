package com.mall.orderservice.service.impl;


import com.mall.common.domain.entity.VoucherOrder;
import com.mall.orderservice.dao.OrderDao;
import com.mall.orderservice.service.VoucherOrderService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VoucherOrderServiceImpl implements VoucherOrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private OrderDao orderDao;

    @Override
    public VoucherOrder createVoucherOrder1(int userId, int voucherId) {


        synchronized (((Integer) userId).toString().intern()) {

            VoucherOrderServiceImpl voucherOrderService = (VoucherOrderServiceImpl) AopContext.currentProxy();
            //这个是获取代理对象，因为我们这个方法是transactional,直接调用就是this，会导致事务失效，所以我们要获取代理对象

            return voucherOrderService.cvo1(userId, voucherId);
        }
    }

    @Transactional
    public VoucherOrder cvo1(int userId, int voucherId) {


        int count=orderDao.selectVoucherOrderCountByUserIdAndVoucherId(userId, voucherId);
        if(count>0){
            return null;
        }//这个是判断用户是否买过这个券，如果买过就不让买了

        int stock = orderDao.getVoucherStock(voucherId);
        if(stock<=0){
            return null;
        }//这个是判断库存是否还有，没有就不让买

        int result = orderDao.decrementVoucherStock(voucherId);
        if(result==0){
            return null;
        }//这个是减库存，如果减库存失败，就不让买

        // 使用 Redis 生成全局唯一 ID
        String orderIdKey = "voucher_order_id"; // Redis 计数器 Key

        Long orderId = redisTemplate.opsForValue().increment(orderIdKey); // 获取自增 ID

        // 生成订单
        VoucherOrder order = new VoucherOrder();
        order.setId(orderId.intValue()); // 订单 ID 由 Redis 生成
        order.setUserId(userId);
        order.setVoucherId(voucherId);
        order.setOrderStatus("PENDING");
        order.setTotalAmount(BigDecimal.ZERO);
        order.setPaymentStatus("UNPAID");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // 插入订单记录
        orderDao.insertVoucherOrder(order);

        return order;


    }


}
//解决超卖就是乐观锁cas
//一人一单的话，需要先找一下数据库里这个用户有没有买过这个券，存在多线程并发的问题，所以需要加锁，这个锁应该是根据用户的id来，因为我们希望对同一个用户加锁，所以锁可以设置为
//userid，但是这里应该用userid.toString(),但是tostring的底层每次都会new一个对象，导致对象可能不是同一个，所以我们用userid.tostring().intern(),保证
//他是从常量池中找字符串，就能保证对象一致。
//锁如果加在方法上，也就是synchronized public VoucherOrder createVoucherOrder1(int userId, int voucherId) {}
//那么这个锁是加在对象上的，也就是这个对象的所有方法都会被锁住，所以我们应该加在代码块上，也就是synchronized (userid.tostring().intern()) {}
//但这个时候问题又来了，因为我们这个方法要transactional，这个锁放方法内部，可能会出现锁释放，事务未提交的情况，此时其他线程来了获得了锁但是事务还没提交看不到这个新的order就会出问题
//所以我们应该把锁放在方法上，但是这个时候我们就要把这个方法放到一个外面的方法里，锁住transactional的方法

//