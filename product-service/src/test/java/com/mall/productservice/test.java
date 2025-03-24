package com.mall.productservice;

import com.mall.common.domain.entity.Product;
import com.mall.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {

    @Autowired
    private ProductService productService;

    @Test
    public void testGet() {
        Product p=productService.getProductById(51);
        System.out.println(p);
    }

    @Test
    public void testUpdate() {

        Product p=new Product();
        p.setId(51);
        p.setStock(112);
        productService.updateProduct(p);
        System.out.println(productService.getAllProducts());
    }
}
