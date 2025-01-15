package com.mall.gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CorsFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"); // Allow all origins
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE"); // Allow methods
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, Content-Type, Authorization"); // Allow headers
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization"); // Expose headers if needed

        // Handle pre-flight requests (OPTIONS)
        if (exchange.getRequest().getMethod().equals(HttpMethod.OPTIONS)) {
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.OK);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange); // Proceed with the request
    }
}
