package com.mall.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the "/images/product/**" URL to the "D:/programme/mainProject3/image/product" directory
        registry.addResourceHandler("/images/products/**")
                .addResourceLocations("file:D:/programme/mainProject3/images/products/");
    }
}
