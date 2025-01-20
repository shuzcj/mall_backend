package com.mall.productservice.controller;


import com.mall.common.domain.vo.ApiResponse;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.domain.dto.StorePageProductQueryParams;
import com.mall.productservice.domain.dto.StorePageProductResponse;
import com.mall.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ApiResponse<String> addProductRequest(AddProductRequest addProductRequest) {
        System.out.println("addProductRequest: "+addProductRequest);
        productService.addProduct(addProductRequest);
        return ApiResponse.success("");
    }

    @GetMapping()
    public ApiResponse<StorePageProductResponse> getProduct(StorePageProductQueryParams storePageProductQueryParams) {
        System.out.println("storePageProductQueryParams: "+storePageProductQueryParams);
        StorePageProductResponse storePageProductResponse = productService.getProductsInStore(storePageProductQueryParams);

        return ApiResponse.success(storePageProductResponse);
    }



}
