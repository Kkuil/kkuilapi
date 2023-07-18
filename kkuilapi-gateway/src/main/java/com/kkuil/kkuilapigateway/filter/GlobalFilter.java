package com.kkuil.kkuilapigateway.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author 小K
 * @Date 2023/7/18 8:30
 * @Description 全局过滤器
 */
@Component
@Slf4j
public class GlobalFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) exchange.getRequest();
        String pathInfo = request.getPathInfo();
        log.debug("请求路径：{}", pathInfo);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
