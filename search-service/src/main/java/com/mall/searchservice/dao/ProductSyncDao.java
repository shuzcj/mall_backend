package com.mall.searchservice.dao;

import com.mall.common.domain.entity.Product;

import java.util.List;

public interface ProductSyncDao {

    List<Product> selectAllProduct();

}
