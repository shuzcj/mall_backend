package com.mall.productservice.service.impl;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloomFilterService {

    private static final String BLOOM_FILTER_NAME = "bloomFilter";

    @Autowired
    private RedissonClient redissonClient;

    // 初始化布隆过滤器
    public void initBloomFilter(long expectedInsertions, double falseProbability) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(BLOOM_FILTER_NAME);
        bloomFilter.tryInit(expectedInsertions, falseProbability);
    }

    // 添加元素到布隆过滤器中
    public void addToBloomFilter(String key) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(BLOOM_FILTER_NAME);
        bloomFilter.add(key);
    }

    // 检查元素是否存在于布隆过滤器中
    public boolean mightContain(String key) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(BLOOM_FILTER_NAME);
        return bloomFilter.contains(key);
    }
}
