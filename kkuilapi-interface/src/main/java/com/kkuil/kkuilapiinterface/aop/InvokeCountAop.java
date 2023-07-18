package com.kkuil.kkuilapiinterface.aop;

import com.kkuil.kkuilapiinterface.anotation.InvokeCount;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.kkuil.kkuilapiinterface.constant.admin.AdminConst.ADMIN_PERMANENT_TOKEN;
import static com.kkuil.kkuilapiinterface.constant.admin.AdminConst.ADMIN_TOKEN_KEY;

/**
 * @Author 小K
 * @Date 2023/7/17 20:00
 * @Description 接口调用次数统计
 */
@Component
@Aspect
public class InvokeCountAop {

    public static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    /**
     * 接口调用次数+1的接口URL
     */
    @Value("${invoke-count.base-url}")
    private String INVOKE_COUNT_BASE_URL;

    /**
     * 接口调用次数+1的接口路径
     */
    @Value("${invoke-count.location}")
    private String INVOKE_COUNT_LOCATION;

    /**
     * @param joinPoint ProceedingJoinPoint
     * @return java.lang.Object
     * @Description 接口调用次数统计
     */
    @Around("@annotation(com.kkuil.kkuilapiinterface.anotation.InvokeCount)")
    public Object invokeCount(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取Method对象
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取调用次数
        InvokeCount invokeCount = method.getAnnotation(InvokeCount.class);
        int id = invokeCount.id();
        // 调用次数+1
        invokeInterfaceForCountPlus(id);
        return joinPoint.proceed();
    }

    private void invokeInterfaceForCountPlus(int id) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(INVOKE_COUNT_BASE_URL).append(INVOKE_COUNT_LOCATION).append("?id=").append(id);
            HttpGet httpGet = new HttpGet(sb.toString());
            httpGet.setHeader(ADMIN_TOKEN_KEY, ADMIN_PERMANENT_TOKEN);
            HttpResponse response = HTTP_CLIENT.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            System.out.println("GET Response: " + responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
