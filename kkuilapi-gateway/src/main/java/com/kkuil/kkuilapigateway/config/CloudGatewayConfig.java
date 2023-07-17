package com.kkuil.kkuilapigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author 小K
 * @Date 2023/7/17 15:00
 * @Description 限流配置
 */
@Configuration
@Slf4j
public class CloudGatewayConfig {
    @Bean(value = "ipKeyResolver")
    KeyResolver ipKeyResolver() {
        return exchange -> {
            String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            log.info("ip:{}", ip);
            return Mono.just(ip);
        };
    }
}
