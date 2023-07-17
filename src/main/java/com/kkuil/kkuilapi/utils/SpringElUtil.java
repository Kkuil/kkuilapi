package com.kkuil.kkuilapi.utils;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @Description spring el表达式解析
 * @Author <a href="https://github.com/Kkuil">Kkuil</a>
 * @Date 2023/7/16 15:20
 */
public class SpringElUtil {
    private static final ExpressionParser PARSER = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * @param method 方法
     * @param args   参数
     * @param spEl   spring el表达式
     * @return 解析后的字符串
     * @Description 解析spel表达式
     */
    public static String parseSpEl(Method method, Object[] args, String spEl) {
        // 解析参数名
        String[] params = Optional.ofNullable(PARAMETER_NAME_DISCOVERER.getParameterNames(method)).orElse(new String[]{});
        // el解析需要的上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            // 所有参数都作为原材料扔进去
            context.setVariable(params[i], args[i]);
        }
        Expression expression = PARSER.parseExpression(spEl);
        return expression.getValue(context, String.class);
    }

    /**
     * @param method 方法
     * @return 方法的唯一key
     * @Description 获取方法的唯一key
     */
    public static String getMethodKey(Method method) {
        return method.getDeclaringClass() + "#" + method.getName();
    }
}
