package com.mall.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // 创建配置对象
        Config config = new Config();

        // 连接本地 Redis，设置密码
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");


        // 创建 RedissonClient 对象
        return Redisson.create(config);
    }
}
