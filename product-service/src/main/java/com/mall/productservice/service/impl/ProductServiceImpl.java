package com.mall.productservice.service.impl;

import cn.hutool.core.lang.UUID;
import com.mall.common.domain.entity.Product;
import com.mall.productservice.dao.ProductDao;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.domain.dto.StorePageProductQueryParams;
import com.mall.productservice.domain.dto.StorePageProductResponse;
import com.mall.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.io.File;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct( AddProductRequest addProductRequest) {
        Product product = new Product();
        if(addProductRequest.getDescription()!=null)
            product.setDescription(addProductRequest.getDescription());
        product.setPrice(addProductRequest.getPrice());
        product.setName(addProductRequest.getProductName());
        product.setUserId(addProductRequest.getUserId());
        product.setStatus(addProductRequest.getStatus());
        product.setSold(0);
        product.setStock(addProductRequest.getStock());
        product.setCategoryId(addProductRequest.getCategoryId());
        LocalDateTime now = LocalDateTime.now();
        product.setCreateTime(now);
        product.setUpdateTime(now);

        // Handle image storage and URL generation
        StringBuilder images = new StringBuilder();
        String uploadDirectory = "D:\\programme\\mainProject3\\images\\products";

        // Ensure the directory exists
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            for (MultipartFile file : addProductRequest.getImages()) {
                // Generate a unique file name
                String originalFilename = file.getOriginalFilename();
                String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

                // Save the file to the disk
                File destinationFile = new File(uploadDirectory + "\\" + uniqueFilename);
                file.transferTo(destinationFile);

                images.append(uniqueFilename).append(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error storing images: " + e.getMessage());
        }

        product.setImageUrls(images.toString());

        productDao.addProduct(product);


    }

    @Override
    public StorePageProductResponse getProductsInStore(StorePageProductQueryParams queryParams) {
        int offset = (queryParams.getPageNumber() - 1) * queryParams.getPageSize();
        Map<String, Object> params = new HashMap<>();
        params.put("userId", queryParams.getUserId());
        params.put("status", Objects.equals(queryParams.getStatus(), "all") ?null:queryParams.getStatus());
        params.put("sort", queryParams.getSort());
        params.put("pageSize", queryParams.getPageSize());
        params.put("offset", offset);

        List<Product> products = productDao.getProducts(params);
        int total = productDao.countProducts(params);

        StorePageProductResponse response = new StorePageProductResponse();
        response.setProducts(products);
        response.setTotal(total);

        return response;
    }


}
