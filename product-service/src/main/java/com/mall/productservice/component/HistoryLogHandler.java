package com.mall.productservice.component;

import com.mall.common.domain.entity.Product;
import com.mall.productservice.domain.dto.Product1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import java.util.Map;

@CanalTable("products")  //需要监听的表
@Component
public class HistoryLogHandler implements EntryHandler<Product1> {//指定表关系实体类

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void insert(Product1 historyLog) {
        //新增数据时执行此方法
        stringRedisTemplate.opsForValue().set("product:productInfo:"+historyLog.getId().toString(),historyLog.toString());

        System.out.println("insert：" + historyLog);
    }

    @Override
    public void update(Product1 before, Product1 after) {
        if(before.getCreatedAt() == null){
            System.out.println("asdasdsadasdasd");

            return;
        }
        System.out.println("update");
        System.out.println("Before: " + before);
        System.out.println("After: " + after);
    }

    @Override
    public void delete(Product1 historyLog) {

        stringRedisTemplate.delete("product:productInfo:"+historyLog.getId().toString());
        //删除数据时执行此方法
        System.out.println("delete：" + historyLog);
    }
}