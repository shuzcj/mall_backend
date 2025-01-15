package com.mall.productservice.service;

import com.mall.productservice.domain.dto.AddProductRequest;

public interface ProductService {

    void addProduct(int userId, AddProductRequest addProductRequest);

}
