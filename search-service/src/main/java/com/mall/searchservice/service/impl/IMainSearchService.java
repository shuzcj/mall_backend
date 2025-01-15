package com.mall.searchservice.service.impl;

import com.mall.searchservice.domain.dto.MainSearchRequest;
import com.mall.searchservice.domain.dto.MainSearchResponse;
import com.mall.searchservice.domain.po.ProductDocument;
import com.mall.searchservice.service.MainSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IMainSearchService implements MainSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public MainSearchResponse search(MainSearchRequest mainSearchRequest) {
        try {
            // Create Elasticsearch search request
            SearchRequest searchRequest = new SearchRequest("products");

            // Build the query
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

            // Full-text search on product name
            if (mainSearchRequest.getQuery() != null && !mainSearchRequest.getQuery().isEmpty()) {
                boolQuery.must(QueryBuilders.matchQuery("name", mainSearchRequest.getQuery()));
            }

            // Price range filter
            if (mainSearchRequest.getMinPrice() != null || mainSearchRequest.getMaxPrice() != null) {
                if (mainSearchRequest.getMinPrice() != null) {
                    boolQuery.filter(QueryBuilders.rangeQuery("price").gte(mainSearchRequest.getMinPrice()));
                }
                if (mainSearchRequest.getMaxPrice() != null) {
                    boolQuery.filter(QueryBuilders.rangeQuery("price").lte(mainSearchRequest.getMaxPrice()));
                }
            }

            // Category filter
            if (mainSearchRequest.getCategories() != null && !mainSearchRequest.getCategories().isEmpty()) {
                boolQuery.filter(QueryBuilders.termsQuery("category", mainSearchRequest.getCategories()));
            }

            // Brand filter
            if (mainSearchRequest.getBrands() != null && !mainSearchRequest.getBrands().isEmpty()) {
                boolQuery.filter(QueryBuilders.termsQuery("brand", mainSearchRequest.getBrands()));
            }

            // Apply the query to the search request directly
            searchRequest.source().query(boolQuery)
                    .from((mainSearchRequest.getPageNum() - 1) * mainSearchRequest.getPageSize()) // Pagination
                    .size(mainSearchRequest.getPageSize());

            // Sorting
            if (mainSearchRequest.getPriceOrder() != null) {
                searchRequest.source().sort("price", mainSearchRequest.getPriceOrder().equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
            }
            if (mainSearchRequest.getSoldOrder() != null) {
                searchRequest.source().sort("sold", mainSearchRequest.getSoldOrder().equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
            }
            if (mainSearchRequest.getUpdateTimeOrder() != null) {
                searchRequest.source().sort("updateTime", mainSearchRequest.getUpdateTimeOrder().equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
            }

            // Add aggregations for categories and brands
            searchRequest.source().aggregation(AggregationBuilders.terms("category_agg").field("category.keyword").size(10));
            searchRequest.source().aggregation(AggregationBuilders.terms("brand_agg").field("brand.keyword").size(10));

            // Execute the search request
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            // Extract product documents
            List<ProductDocument> productDocuments = StreamSupport.stream(searchResponse.getHits().spliterator(), false)
                    .map(hit -> {
                        ProductDocument document = new ProductDocument();
                        document.setId(hit.getId());
                        document.setName((String) hit.getSourceAsMap().get("name"));
                        document.setPrice((Integer) hit.getSourceAsMap().get("price"));
                        document.setStock((Integer) hit.getSourceAsMap().get("stock"));
                        document.setImageUrl((String) hit.getSourceAsMap().get("imageUrl"));
                        document.setCategory((String) hit.getSourceAsMap().get("category"));
                        document.setSold((Integer) hit.getSourceAsMap().get("sold"));
                        document.setBusinessId((Integer) hit.getSourceAsMap().get("businessId"));
                        document.setUpdateTime(LocalDateTime.parse(hit.getSourceAsMap().get("updateTime").toString()));
                        document.setBrand((String) hit.getSourceAsMap().get("brand"));
                        return document;
                    })
                    .collect(Collectors.toList());

            // Extract category and brand lists from the aggregations
            Terms categoryTerms = searchResponse.getAggregations().get("category_agg");
            List<String> categoryList = categoryTerms.getBuckets().stream()
                    .map(Terms.Bucket::getKeyAsString)
                    .collect(Collectors.toList());

            Terms brandTerms = searchResponse.getAggregations().get("brand_agg");
            List<String> brandList = brandTerms.getBuckets().stream()
                    .map(Terms.Bucket::getKeyAsString)
                    .collect(Collectors.toList());

            // Create and return the response
            MainSearchResponse response = new MainSearchResponse();
            response.setProductDocuments(productDocuments);
            response.setCategoryList(categoryList);
            response.setBrandList(brandList);

            return response;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
