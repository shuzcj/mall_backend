package com.mall.orderservice.domain.dto;

public class VoucherOrderRequest {
    private Integer voucherId;
    private Integer userId;

    public VoucherOrderRequest() {
    }

    public VoucherOrderRequest(Integer voucherId, Integer userId) {
        this.voucherId = voucherId;
        this.userId = userId;
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

    public String toString() {
        return "VoucherOrderRequest{voucherId = " + voucherId + ", userId = " + userId + "}";
    }
}
