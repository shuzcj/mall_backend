package com.mall.searchservice.domain.dto;

import java.util.List;

/*
    * Search request object
    *
    * Order String: asc/desc/null
 */

public class MainSearchRequest {
    private String query;               // Full text search for product name
    private Integer minPrice;           // Minimum price filter
    private Integer maxPrice;           // Maximum price filter
    private String priceOrder;          // Price order (asc/desc)
    private String soldOrder;           // Sold order (asc/desc)
    private String updateTimeOrder;     // Update time order (asc/desc)
    private List<String> categories;    // List of categories to filter by
    private List<String> brands;        // List of brands to filter by
    private int pageNum = 1;               // Page number for pagination
    private int pageSize = 10;              // Number of items per page


    public MainSearchRequest() {
    }

    public MainSearchRequest(String query, Integer minPrice, Integer maxPrice, String priceOrder, String soldOrder, String updateTimeOrder, List<String> categories, List<String> brands, int pageNum, int pageSize) {
        this.query = query;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.priceOrder = priceOrder;
        this.soldOrder = soldOrder;
        this.updateTimeOrder = updateTimeOrder;
        this.categories = categories;
        this.brands = brands;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 获取
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * 设置
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * 获取
     * @return minPrice
     */
    public Integer getMinPrice() {
        return minPrice;
    }

    /**
     * 设置
     * @param minPrice
     */
    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 获取
     * @return maxPrice
     */
    public Integer getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置
     * @param maxPrice
     */
    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取
     * @return priceOrder
     */
    public String getPriceOrder() {
        return priceOrder;
    }

    /**
     * 设置
     * @param priceOrder
     */
    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    /**
     * 获取
     * @return soldOrder
     */
    public String getSoldOrder() {
        return soldOrder;
    }

    /**
     * 设置
     * @param soldOrder
     */
    public void setSoldOrder(String soldOrder) {
        this.soldOrder = soldOrder;
    }

    /**
     * 获取
     * @return updateTimeOrder
     */
    public String getUpdateTimeOrder() {
        return updateTimeOrder;
    }

    /**
     * 设置
     * @param updateTimeOrder
     */
    public void setUpdateTimeOrder(String updateTimeOrder) {
        this.updateTimeOrder = updateTimeOrder;
    }

    /**
     * 获取
     * @return categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * 设置
     * @param categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * 获取
     * @return brands
     */
    public List<String> getBrands() {
        return brands;
    }

    /**
     * 设置
     * @param brands
     */
    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    /**
     * 获取
     * @return pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置
     * @param pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获取
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "MainSearchRequest{query = " + query + ", minPrice = " + minPrice + ", maxPrice = " + maxPrice + ", priceOrder = " + priceOrder + ", soldOrder = " + soldOrder + ", updateTimeOrder = " + updateTimeOrder + ", categories = " + categories + ", brands = " + brands + ", pageNum = " + pageNum + ", pageSize = " + pageSize + "}";
    }
}
