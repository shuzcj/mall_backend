package com.mall.productservice.service.impl;

import cn.hutool.core.lang.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.domain.dto.StockUpdateResponse;
import com.mall.common.domain.entity.Product;
import com.mall.productservice.dao.ProductDao;
import com.mall.productservice.domain.dto.AddProductRequest;
import com.mall.productservice.domain.dto.StorePageProductQueryParams;
import com.mall.productservice.domain.dto.StorePageProductResponse;
import com.mall.productservice.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

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
        product.setCreatedAt(now);
        product.setUpdatedAt(now);

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
                String uniqueFilename = UUID.randomUUID() + ".jpg";

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

    @Override
    public List<Product>getAllProducts(){
        return productDao.getAllProducts();
    }


    @Override
    public StockUpdateResponse updateStock(Integer productId, Integer stock) {
        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        int currentStock = product.getStock();

        if (stock < 0) {
            if (currentStock >= Math.abs(stock)) {
                // If enough stock, perform the deduction
                if (productDao.deductStock(productId, Math.abs(stock)) > 0) {//if the rows which are updated are more than 0
                    // Calculate total price deduction
                    BigDecimal pricePerUnit = product.getPrice();
                    return new StockUpdateResponse(true, pricePerUnit);//return the single price of the product
                } else {
                    return new StockUpdateResponse(false, new BigDecimal(-1));
                }
            }
            return new StockUpdateResponse(false, new BigDecimal(-1)); // Not enough stock
        } else {
            // Add stock
            productDao.addStock(productId, stock);
            return new StockUpdateResponse(true, BigDecimal.ZERO); // No monetary value involved in adding stock
        }
    }










    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BloomFilterService bloomFilterService;

    @Override
    public Product getProductById(Integer productId) {

        // 使用布隆过滤器防止缓存穿透
        if (!bloomFilterService.mightContain(productId.toString())) {
            System.out.println("布隆过滤器拦截了一个不存在的商品 ID: " + productId);
            return null;
        }

        String cacheKey = "product:productInfo:" + productId;
        String lockKey = "product:lock:" + productId;

        System.out.println("尝试redis获取"+productId);
        // 1. 查询缓存
        String cachedJson = redisTemplate.opsForValue().get(cacheKey);
        if (cachedJson != null) {
            try {
                return objectMapper.readValue(cachedJson, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("缓存解析失败", e);
            }
        }
        System.out.println("redis未命中"+productId);
        // 2. 未命中，尝试加锁回源
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(); // 阻塞式获取锁
        System.out.println("获取到锁"+productId+"，开始回源"+lock);
        try {
            // 双检缓存
            cachedJson = redisTemplate.opsForValue().get(cacheKey);
            if (cachedJson != null) {
                return objectMapper.readValue(cachedJson, Product.class);
            }

            // 查询数据库
            Product product = productDao.getProductById(productId);
            if (product != null) {
                // 加随机过期时间，防雪崩
                int baseTtl = 300;
                int randomTtl = new Random().nextInt(300);
                String json = objectMapper.writeValueAsString(product);
                redisTemplate.opsForValue().set(cacheKey, json, baseTtl + randomTtl, TimeUnit.SECONDS);
            }

            return product;

        } catch (JsonProcessingException e) {
            throw new RuntimeException("缓存序列化失败", e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void updateProduct(Product product) {
        Integer productId = product.getId();
        String lockKey = "product:lock:" + productId;
        String cacheKey = "product:productInfo:" + productId;

        RLock lock = redissonClient.getLock(lockKey);
        System.out.println("update尝试获取锁"+productId);
        lock.lock(); // 阻塞式获取锁
        try {
            System.out.println("update获取到锁"+productId+"，开始"+lock);
            // 1. 更新数据库
            productDao.updateProduct(product);

            // 2. 删除缓存
            redisTemplate.delete(cacheKey);

        } finally {
            // 3. 释放锁
            lock.unlock();
        }
    }

























}
