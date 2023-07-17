package com.kkuil.kkuilapiinterface.aop;

import com.kkuil.kkuilapiinterface.anotation.InvokeCount;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
            HttpGet httpGet = new HttpGet("http://127.0.0.1:3170/api/interface/invoke-count" + "?id=" + id);
            httpGet.setHeader(ADMIN_TOKEN_KEY, ADMIN_PERMANENT_TOKEN);
            HttpResponse response = HTTP_CLIENT.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            System.out.println("GET Response: " + responseString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 发送GET请求
            try {
                HTTP_CLIENT.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
