package com.mall.productservice.component;




import com.mall.common.domain.entity.Product;
import com.mall.productservice.service.ProductService;
import com.mall.productservice.service.impl.BloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BloomFilterInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private BloomFilterService bloomFilterService;


    @Override
    public void run(String... args) throws Exception {

        // 查询所有产品数据
        List<Product> all = productService.getAllProducts();

        // 初始化布隆过滤器
        bloomFilterService.initBloomFilter(all.size(), 0.01);

        // 将所有产品的ID加入布隆过滤器
        all.forEach(product -> {
            System.out.println("Adding product to bloom filter: " + product.getId());
            bloomFilterService.addToBloomFilter(product.getId().toString());
        });

        System.out.println("BloomFilter initialized");
    }
}
