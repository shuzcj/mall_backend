package com.mall.productservice.controller;


import com.mall.common.domain.dto.StockUpdateResponse;
import com.mall.common.domain.entity.Product;
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
    public ApiResponse<String> addProductRequest(@RequestBody AddProductRequest addProductRequest) {
        System.out.println("addProductRequest: "+addProductRequest);
        productService.addProduct(addProductRequest);
        return ApiResponse.success("");
    }

    @GetMapping()
    public ApiResponse<StorePageProductResponse> getStorePageProduct(StorePageProductQueryParams storePageProductQueryParams) {
        System.out.println("storePageProductQueryParams: "+storePageProductQueryParams);
        StorePageProductResponse storePageProductResponse = productService.getProductsInStore(storePageProductQueryParams);

        return ApiResponse.success(storePageProductResponse);
    }

    // Get a single product by ID
    @GetMapping("/{productId}")
    public ApiResponse<Product> getProductById(@PathVariable Integer productId) {
        System.out.println("Fetching product with ID: " + productId);
        Product product = productService.getProductById(productId);

        return ApiResponse.success(product);
    }

    @PutMapping("/{productId}")
    public ApiResponse<String> updateProduct(@PathVariable Integer productId, @RequestBody AddProductRequest addProductRequest) {
        System.out.println("Updating product with ID: " + productId);
        //productService.updateProduct(productId, addProductRequest);

        return ApiResponse.success("");
    }

    @PostMapping("updateStock")
    public StockUpdateResponse updateStock(@RequestParam("productId") Integer productId, @RequestParam("stock") Integer stock){

        return productService.updateStock(productId, stock);
    }

}
