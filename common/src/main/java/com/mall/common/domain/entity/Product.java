package com.mall.common.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "image_urls", length = 1023)
    private String imageUrls;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "sold", nullable = true, columnDefinition = "int default 0")
    private Integer sold;

    @Column(name = "status", nullable = false, columnDefinition = "enum('active', 'inactive', 'deleted') default 'active'")
    private String status;

    @Column(name = "create_time", nullable = true, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = true, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

    @Column(name = "business_id", nullable = false)
    private Integer businessId;

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price, Integer stock, String imageUrls, Integer categoryId, String brand, Integer sold, String status, LocalDateTime createTime, LocalDateTime updateTime, Integer businessId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageUrls = imageUrls;
        this.categoryId = categoryId;
        this.brand = brand;
        this.sold = sold;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.businessId = businessId;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return imageUrls
     */
    public String getImageUrls() {
        return imageUrls;
    }

    /**
     * 设置
     * @param imageUrls
     */
    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
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
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取
     * @return sold
     */
    public Integer getSold() {
        return sold;
    }

    /**
     * 设置
     * @param sold
     */
    public void setSold(Integer sold) {
        this.sold = sold;
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
     * @return createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return updateTime
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     * @param updateTime
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取
     * @return businessId
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * 设置
     * @param businessId
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String toString() {
        return "Product{id = " + id + ", name = " + name + ", price = " + price + ", stock = " + stock + ", imageUrls = " + imageUrls + ", categoryId = " + categoryId + ", brand = " + brand + ", sold = " + sold + ", status = " + status + ", createTime = " + createTime + ", updateTime = " + updateTime + ", businessId = " + businessId + "}";
    }


}
