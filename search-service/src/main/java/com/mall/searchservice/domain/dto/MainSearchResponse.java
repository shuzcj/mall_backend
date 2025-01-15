package com.mall.searchservice.domain.dto;

import com.mall.searchservice.domain.po.ProductDocument;

import java.util.List;

public class MainSearchResponse {

    private List<ProductDocument> productDocuments;
    private List<String> categoryList;
    private List<String> brandList;


    public MainSearchResponse() {
    }

    public MainSearchResponse(List<ProductDocument> productDocuments, List<String> categoryList, List<String> brandList) {
        this.productDocuments = productDocuments;
        this.categoryList = categoryList;
        this.brandList = brandList;
    }

    /**
     * 获取
     * @return productDocuments
     */
    public List<ProductDocument> getProductDocuments() {
        return productDocuments;
    }

    /**
     * 设置
     * @param productDocuments
     */
    public void setProductDocuments(List<ProductDocument> productDocuments) {
        this.productDocuments = productDocuments;
    }

    /**
     * 获取
     * @return categoryList
     */
    public List<String> getCategoryList() {
        return categoryList;
    }

    /**
     * 设置
     * @param categoryList
     */
    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * 获取
     * @return brandList
     */
    public List<String> getBrandList() {
        return brandList;
    }

    /**
     * 设置
     * @param brandList
     */
    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public String toString() {
        return "MainSearchResponse{productDocuments = " + productDocuments + ", categoryList = " + categoryList + ", brandList = " + brandList + "}";
    }
}
