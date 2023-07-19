package com.kkuil.kkuilapigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @Author 小K
 * @Date 2023/7/18 18:00
 * @Description 限流器
 */
@Component
@Slf4j
public class UriKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        final String path = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).toString();
        log.debug("请求地址：{}", path);
        return Mono.just(path);
    }
}
