package com.kkuil.kkuilapiinterface.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author 小K
 * @Date 2023/7/17 8:00
 */
@Slf4j
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String flag = httpRequest.getHeader("X-Request-Flag");
        if (flag == null) {
            log.info("非法请求");
        }
        log.info("请求通过:{}", flag);
    }
}
