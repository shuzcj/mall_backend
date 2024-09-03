package com.mall.searchservice;

import com.mall.searchservice.service.ProductSyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceApplicationTests {

    @Autowired
    private ProductSyncService productSyncService;

    @Test
    void contextLoads() {
    }

    @Test
    void testMain() {
        productSyncService.syncAllProducts();
    }

}
