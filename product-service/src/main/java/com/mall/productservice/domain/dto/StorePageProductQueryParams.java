package com.mall.productservice.domain.dto;

public class StorePageProductQueryParams {

    private String sort;
    private String status;
    private Integer userId;
    private Integer pageNumber;
    private Integer pageSize;


    public StorePageProductQueryParams() {
    }

    public StorePageProductQueryParams(String sort, String status, Integer userId, Integer pageNumber, Integer pageSize) {
        this.sort = sort;
        this.status = status;
        this.userId = userId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * 获取
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * 设置
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort;
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
     * @return pageNumber
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置
     * @param pageNumber
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获取
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "StorePageProductQueryParams{sort = " + sort + ", status = " + status + ", userId = " + userId + ", pageNumber = " + pageNumber + ", pageSize = " + pageSize + "}";
    }
}
