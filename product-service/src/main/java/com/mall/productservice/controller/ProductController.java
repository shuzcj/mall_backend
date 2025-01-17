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
    public ApiResponse<String> addProductRequest(
            @RequestHeader(value = "user-info", required = false) String userId,
                                                 AddProductRequest addProductRequest) {
        System.out.println("userId: "+userId);
        System.out.println("addProductRequest: "+addProductRequest);
        productService.addProduct(Integer.parseInt(userId), addProductRequest);
        return ApiResponse.success("");
    }

    @GetMapping()
    public ApiResponse<StorePageProductResponse> getProduct(
            @RequestHeader(value = "user-info", required = false) Integer userId,
            StorePageProductQueryParams storePageProductQueryParams) {
        System.out.println("storePageProductQueryParams: "+storePageProductQueryParams);
        StorePageProductResponse storePageProductResponse = productService.getProductsInStore(userId, storePageProductQueryParams);

        return ApiResponse.success(storePageProductResponse);
    }



}
