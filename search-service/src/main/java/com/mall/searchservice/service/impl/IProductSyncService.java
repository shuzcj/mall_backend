package com.mall.searchservice.service.impl;

import com.mall.common.domain.entity.Product;
import com.mall.searchservice.dao.ProductSyncDao;
import com.mall.searchservice.domain.po.ProductDocument;
import com.mall.searchservice.service.ProductSyncService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.bean.BeanUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class IProductSyncService implements ProductSyncService {

    @Autowired
    private ProductSyncDao productSyncDao;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void syncAllProducts(){
        List<Product> products = productSyncDao.selectAllProduct();

        // Convert Product entities to ProductDocument objects using Hutool
        List<ProductDocument> productDocuments = products.stream()
                .map(product -> BeanUtil.copyProperties(product, ProductDocument.class))
                .collect(Collectors.toList());

        System.out.println(productDocuments);

        // Create a BulkRequest
        BulkRequest bulkRequest = new BulkRequest();

        // Add each ProductDocument to the BulkRequest
        for (ProductDocument productDocument : productDocuments) {
            IndexRequest indexRequest = new IndexRequest("products")
                    .id(productDocument.getId())  // Use the product ID as the document ID
                    .source(BeanUtil.beanToMap(productDocument), XContentType.JSON);  // Convert document to JSON

            bulkRequest.add(indexRequest);  // Add index request to bulk request
        }

        try {
            // Execute the BulkRequest
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

            // Handle the response
            if (bulkResponse.hasFailures()) {
                System.out.println("Bulk operation had failures: " + bulkResponse.buildFailureMessage());
            } else {
                System.out.println("Bulk operation completed successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();  // Handle exceptions
        }


    }


}
