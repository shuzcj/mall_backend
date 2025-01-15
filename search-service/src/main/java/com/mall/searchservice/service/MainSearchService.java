package com.mall.searchservice.service;

import com.mall.searchservice.domain.dto.MainSearchRequest;
import com.mall.searchservice.domain.dto.MainSearchResponse;
import com.mall.searchservice.domain.po.ProductDocument;

import java.util.List;

public interface MainSearchService {

    public MainSearchResponse search(MainSearchRequest mainSearchRequest);

}
