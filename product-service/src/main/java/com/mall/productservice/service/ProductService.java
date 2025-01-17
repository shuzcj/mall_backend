package com.mall.productservice.service;

import com.mall.common.domain.entity.Product;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.domain.dto.StorePageProductQueryParams;
import com.mall.productservice.domain.dto.StorePageProductResponse;

import java.util.List;

public interface ProductService {

    void addProduct(int userId, AddProductRequest addProductRequest);

    StorePageProductResponse getProductsInStore(Integer userId, StorePageProductQueryParams storePageProductQueryParams);

}
