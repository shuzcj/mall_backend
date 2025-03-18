package com.mall.common.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
public class Vouchers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 自增长
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "pay_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal payValue;

    @Column(name = "actual_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal actualValue;

    @Column(name = "status", nullable = false)
    private Byte status;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;


    public Vouchers() {
    }

    public Vouchers(Integer id, Integer userId, String title, BigDecimal payValue, BigDecimal actualValue, Byte status, Integer stock, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.payValue = payValue;
        this.actualValue = actualValue;
        this.status = status;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return payValue
     */
    public BigDecimal getPayValue() {
        return payValue;
    }

    /**
     * 设置
     * @param payValue
     */
    public void setPayValue(BigDecimal payValue) {
        this.payValue = payValue;
    }

    /**
     * 获取
     * @return actualValue
     */
    public BigDecimal getActualValue() {
        return actualValue;
    }

    /**
     * 设置
     * @param actualValue
     */
    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }

    /**
     * 获取
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
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
     * @return createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置
     * @param createdAt
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取
     * @return updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置
     * @param updatedAt
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        return "Voucher{id = " + id + ", userId = " + userId + ", title = " + title + ", payValue = " + payValue + ", actualValue = " + actualValue + ", status = " + status + ", stock = " + stock + ", createdAt = " + createdAt + ", updatedAt = " + updatedAt + "}";
    }
}
