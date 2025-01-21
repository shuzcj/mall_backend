package com.mall.orderservice.domain;

public class SimpleOrderItem {
    private Integer id;
    private Integer quantity;

    public SimpleOrderItem() {
    }

    public SimpleOrderItem(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "OrderItem{id = " + id + ", quantity = " + quantity + "}";
    }
}
