package com.kkuil.kkuilapi.aop;

import cn.hutool.core.bean.BeanUtil;
import com.kkuil.kkuilapi.model.bo.admin.AdminInfoInToken;
import com.kkuil.kkuilapi.model.po.TbAdminInfo;
import com.kkuil.kkuilapi.service.ITbAdminInfoService;
import com.kkuil.kkuilapi.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;

import static com.kkuil.kkuilapi.constant.AdminConst.ADMIN_TOKEN_KEY;
import static com.kkuil.kkuilapi.constant.AdminConst.ADMIN_TOKEN_SECRET;

/**
 * @Author 小K
 * @Date 2023/7/14 16:30
 * @Description 管理员拦截器
 */
@Configuration
@Aspect
@Slf4j
public class AuthAdminAOP {

    @Resource
    private ITbAdminInfoService adminService;

    @Around("@annotation(com.kkuil.kkuilapi.anotation.AuthAdmin)")
    public Object adminInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        // 获取请求对象
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        // 拦截处理逻辑
        String tokenInHeader = request.getHeader(ADMIN_TOKEN_KEY);
        log.info("token-interceptor: {}", tokenInHeader);
        // 1. 验证token是否为空
        if (tokenInHeader == null) {
            throw new SecurityException("Access denied");
        }

        // 2. 验证token的有效性
        // 2.1 验证token未过期并是有效的
        Claims payload = null;
        try {
            payload = TokenUtil.parse(tokenInHeader, ADMIN_TOKEN_SECRET);
        } catch (Exception e) {
            throw new SecurityException("Access denied");
        }
        if (payload == null) {
            throw new SecurityException("Access denied");
        }

        // 2.2 验证token中的用户是真实用户
        String id = payload.get("id").toString();
        TbAdminInfo adminInfo = adminService.getById(id);
        if (adminInfo == null) {
            throw new SecurityException("Access denied");
        }

        // 3. 刷新token
        AdminInfoInToken adminInfoInToken = new AdminInfoInToken();
        adminInfoInToken.setId(id);
        String token = TokenUtil.create((HashMap<String, Object>) BeanUtil.beanToMap(adminInfoInToken), ADMIN_TOKEN_SECRET);
        assert response != null;
        response.setHeader(ADMIN_TOKEN_KEY, token);
        return joinPoint.proceed();
    }
}