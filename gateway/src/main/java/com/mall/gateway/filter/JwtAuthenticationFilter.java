package com.mall.gateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.mall.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1) // Ensures the execution order of this filter
public class JwtAuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        System.out.println("Request path: " + path);
        List<String> whiteList = Arrays.asList("/user/login", "/user/register","/images/**");

        // Instantiate AntPathMatcher
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        // Check if the path matches any pattern in the whitelist
        boolean isWhitelisted = whiteList.stream().anyMatch(pattern -> {
            return antPathMatcher.match(pattern, path);
        });

        if (isWhitelisted) {
            System.out.println("Whitelist path accessed: " + path + " - Skipping authentication");
            return chain.filter(exchange);
        }

        // Get the Authorization header
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // If the Authorization header is missing or doesn't start with "Bearer ", deny access
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Unauthorized access attempt - Missing or invalid Authorization header");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extract the JWT token
        String token = authHeader.substring(7);

        try {
            // Validate and parse the JWT
            Claims claims = jwtUtil.parseJWT(token);

            // Retrieve the user ID from the token
            String userId = claims.getSubject();
            System.out.println("Authentication successful for user: " + userId);

            // Add the user ID to the request header for downstream services
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(builder -> builder.header("user-info", userId))
                    .build();

            // Allow the request to pass through with the modified exchange
            return chain.filter(modifiedExchange);

        } catch (Exception e) {
            // If JWT parsing fails (e.g., expired, invalid), log the error and deny access
            System.out.println("Authentication failed - Error: " + e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
