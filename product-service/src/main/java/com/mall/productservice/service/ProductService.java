package com.mall.productservice.service;

import com.mall.common.domain.dto.StockUpdateResponse;
import com.mall.common.domain.entity.Product;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.domain.dto.StorePageProductQueryParams;
import com.mall.productservice.domain.dto.StorePageProductResponse;

import java.util.List;

public interface ProductService {

    void addProduct(AddProductRequest addProductRequest);

    StorePageProductResponse getProductsInStore(StorePageProductQueryParams storePageProductQueryParams);

    Product getProductById(Integer productId);

    List<Product> getAllProducts();

    StockUpdateResponse updateStock(Integer productId, Integer stock);

    void updateProduct(Product product);
}
