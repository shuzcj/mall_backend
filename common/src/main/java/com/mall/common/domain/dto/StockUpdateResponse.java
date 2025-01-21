package com.mall.common.domain.dto;

import java.math.BigDecimal;

public class StockUpdateResponse {
    private boolean success;
    private BigDecimal value;


    public StockUpdateResponse() {
    }

    public StockUpdateResponse(boolean success, BigDecimal value) {
        this.success = success;
        this.value = value;
    }

    /**
     * 获取
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取
     * @return value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * 设置
     * @param value
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String toString() {
        return "StockUpdateResponse{success = " + success + ", value = " + value + "}";
    }
}
