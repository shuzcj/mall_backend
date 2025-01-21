package com.mall.productservice.service;

import com.mall.common.domain.dto.StockUpdateResponse;
import com.mall.common.domain.entity.Product;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.domain.dto.StorePageProductQueryParams;
import com.mall.productservice.domain.dto.StorePageProductResponse;

public interface ProductService {

    void addProduct(AddProductRequest addProductRequest);

    StorePageProductResponse getProductsInStore(StorePageProductQueryParams storePageProductQueryParams);

    Product getProductById(Integer productId);

    StockUpdateResponse updateStock(Integer productId, Integer stock);
}
