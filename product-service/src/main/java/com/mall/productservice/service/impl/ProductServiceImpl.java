package com.mall.productservice.service.impl;

import cn.hutool.core.lang.UUID;
import com.mall.common.domain.entity.Product;
import com.mall.productservice.dao.ProductDao;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.io.File;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(int userId, AddProductRequest addProductRequest) {
        Product product = new Product();
        if(addProductRequest.getDescription()!=null)
            product.setDescription(addProductRequest.getDescription());
        product.setPrice(addProductRequest.getPrice());
        product.setName(addProductRequest.getProductName());
        product.setUserId(userId);
        product.setStatus(addProductRequest.getStatus());
        product.setSold(0);
        product.setStock(addProductRequest.getStock());
        product.setCategoryId(addProductRequest.getCategoryId());
        LocalDateTime now = LocalDateTime.now();
        product.setCreateTime(now);

        // Handle image storage and URL generation
        StringBuilder images = new StringBuilder();
        String uploadDirectory = "D:\\programme\\mainProject3\\image\\product";

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
}
