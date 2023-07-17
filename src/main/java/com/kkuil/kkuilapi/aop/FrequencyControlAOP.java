package com.kkuil.kkuilapi.aop;

import cn.hutool.core.util.StrUtil;
import com.kkuil.kkuilapi.anotation.FrequencyControl;
import com.kkuil.kkuilapi.exception.thrower.FrequencyControlException;
import com.kkuil.kkuilapi.utils.RedisUtils;
import com.kkuil.kkuilapicommon.common.ErrorCode;
import com.kkuil.kkuilapi.utils.SpringElUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description 频率控制拦截器
 */
@Slf4j
@Aspect
@Component
public class FrequencyControlAOP {

    @Around("@annotation(com.kkuil.kkuilapi.anotation.FrequencyControl)||@annotation(com.kkuil.kkuilapi.anotation.FrequencyControlContainer)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取Method对象
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取频控容器
        FrequencyControl[] frequencyContainer = method.getAnnotationsByType(FrequencyControl.class);
        // 初始化一个频控map
        Map<String, FrequencyControl> frequerncyMap = new HashMap<>();
        // 遍历频控容器
        for (int i = 0; i < frequencyContainer.length; i++) {
            // 获取频控对象
            FrequencyControl frequencyControl = frequencyContainer[i];
            // 获取频控前缀（默认方法限定名+注解排名（可能多个））
            String prefix =
                    StrUtil.isBlank(frequencyControl.prefixKey())
                            ? SpringElUtil.getMethodKey(method) + ":index:" + i
                            : frequencyControl.prefixKey();
            // 获取频控目标
            String key = switch (frequencyControl.target()) {
                case EL -> SpringElUtil.parseSpEl(method, joinPoint.getArgs(), frequencyControl.spEl());
                // 每个IP限制
                case IP -> request.getRemoteAddr();
            };
            // 将频控对象放入map
            frequerncyMap.put(prefix + ":" + key, frequencyControl);
        }
        // 批量获取redis统计的值
        ArrayList<String> keyList = new ArrayList<>(frequerncyMap.keySet());
        List<Integer> countList = RedisUtils.getBatch(keyList, Integer.class);
        for (int i = 0; i < keyList.size(); i++) {
            // 获取redis中的所有key
            String key = keyList.get(i);
            // 获取redis中的所有value
            Integer count = countList.get(i);
            // 通过key获取频控对象
            FrequencyControl frequencyControl = frequerncyMap.get(key);
            if (Objects.nonNull(count) && count >= frequencyControl.count()) {
                // 超频
                log.warn("frequencyControl limit key:{},count:{}", key, count);
                throw new FrequencyControlException(ErrorCode.FREQUENCY_LIMIT_ERROR);
            }
        }
        try {
            return joinPoint.proceed();
        } finally {
            // 不管成功还是失败，都增加次数
            frequerncyMap.forEach((k, v) -> {
                RedisUtils.inc(k, v.time(), v.unit());
            });
        }
    }
}
