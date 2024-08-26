package com.mall.gateway.filter;

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

import java.util.Arrays;
import java.util.List;

@Component
@Order(1) // 确保这个过滤器的执行顺序
public class JwtAuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        List<String> whiteList = Arrays.asList("/user/login", "/user/register");

        // 如果路径在白名单中，则直接放行
        if (whiteList.contains(path)) {
            return chain.filter(exchange);
        }

        // 获取请求头中的 Authorization
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // 如果 Authorization 为空或者不以 "Bearer " 开头，直接返回未授权
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 提取 JWT
        String token = authHeader.substring(7);

        try {
            // 验证并解析 JWT
            Claims claims = jwtUtil.parseJWT(token);

            // 将用户 ID 存入请求头，以便下游服务使用
            String userId = claims.getSubject();
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(builder -> builder.header("user-info", userId))
                    .build();

            // 放行请求，传递修改后的 Exchange
            return chain.filter(modifiedExchange);

        } catch (Exception e) {
            // 如果 JWT 解析失败（如过期、无效等），返回未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
