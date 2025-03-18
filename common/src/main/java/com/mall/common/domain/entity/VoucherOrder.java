package com.mall.common.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "voucher_orders")
public class VoucherOrder {

    @Id
    @Column(name = "id") // 需要手动输入 ID
    private Integer id;

    @Column(name = "voucher_id", nullable = false)
    private Integer voucherId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_status", length = 255)
    private String orderStatus;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "payment_status", length = 255)
    private String paymentStatus;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;


    public VoucherOrder() {
    }

    public VoucherOrder(Integer id, Integer voucherId, Integer userId, String orderStatus, BigDecimal totalAmount, String paymentStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.voucherId = voucherId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
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
     * @return voucherId
     */
    public Integer getVoucherId() {
        return voucherId;
    }

    /**
     * 设置
     * @param voucherId
     */
    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
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
     * @return orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置
     * @param orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取
     * @return totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置
     * @param totalAmount
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取
     * @return paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 设置
     * @param paymentStatus
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        return "VoucherOrder{id = " + id + ", voucherId = " + voucherId + ", userId = " + userId + ", orderStatus = " + orderStatus + ", totalAmount = " + totalAmount + ", paymentStatus = " + paymentStatus + ", createdAt = " + createdAt + ", updatedAt = " + updatedAt + "}";
    }
}
