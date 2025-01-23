package com.mall.productservice.dao;

import com.mall.common.domain.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductDao {

    void addProduct(Product product);

    List<Product> getProducts(Map<String, Object> queryParams);
    Integer countProducts(Map<String, Object> queryParams);

    Product getProductById(Integer productId);

    Integer checkStock(Integer productId);

    Integer deductStock(@Param("productId") Integer productId, @Param("stock") Integer stock);
    Integer addStock(@Param("productId") Integer productId, @Param("stock") Integer stock);

}
