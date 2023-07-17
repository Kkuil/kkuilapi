package com.kkuil.kkuilapiinterface.config;

import com.kkuil.kkuilapiinterface.filter.GlobalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小K
 * @Date 2023/7/17 10:00
 * @Description 过滤器配置
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registrationBean1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new GlobalFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
