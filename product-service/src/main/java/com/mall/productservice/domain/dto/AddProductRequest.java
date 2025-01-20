package com.mall.productservice.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class AddProductRequest {

    private Integer userId;               // User ID
    private String productName;       // Name of the product
    private String description;       // Description of the product
    private BigDecimal price;         // Price of the product
    private Integer stock;            // Stock quantity
    private Integer categoryId;       // Category ID
    private String status;            // Status (e.g., 'active', 'inactive', 'deleted')
    private List<MultipartFile> images;       // List of uploaded images


    public AddProductRequest() {
    }

    public AddProductRequest(Integer userId, String productName, String description, BigDecimal price, Integer stock, Integer categoryId, String status, List<MultipartFile> images) {
        this.userId = userId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.status = status;
        this.images = images;
    }

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取
     * @return stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置
     * @param stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取
     * @return categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取
     * @return images
     */
    public List<MultipartFile> getImages() {
        return images;
    }

    /**
     * 设置
     * @param images
     */
    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public String toString() {
        return "AddProductRequest{userId = " + userId + ", productName = " + productName + ", description = " + description + ", price = " + price + ", stock = " + stock + ", categoryId = " + categoryId + ", status = " + status + ", images = " + images + "}";
    }
}
