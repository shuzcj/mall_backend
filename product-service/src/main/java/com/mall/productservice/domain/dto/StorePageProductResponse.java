package com.mall.productservice.domain.dto;

import com.mall.common.domain.entity.Product;

import java.util.List;

public class StorePageProductResponse {
    private Integer total;
    private List<Product> products;


    public StorePageProductResponse() {
    }

    public StorePageProductResponse(Integer total, List<Product> products) {
        this.total = total;
        this.products = products;
    }

    /**
     * 获取
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取
     * @return products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * 设置
     * @param products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String toString() {
        return "StorePageProductResponse{total = " + total + ", products = " + products + "}";
    }
}
