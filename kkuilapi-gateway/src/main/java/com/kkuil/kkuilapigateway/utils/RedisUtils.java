package com.kkuil.kkuilapigateway.utils;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author 小K
 * @Date 2023/7/16 13:15
 * @Description Redis工具类
 */
@Slf4j
public class RedisUtils {

    private static StringRedisTemplate stringRedisTemplate;

    // 解决非springboot项目无法注入的问题
    static {
        RedisUtils.stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    }

    /**
     * @Description 原子性的设置缓存Lua脚本
     */
    private static final String LUA_INCR_EXPIRE =
            "local key,ttl=KEYS[1],ARGV[1] \n" +
                    " \n" +
                    "if redis.call('EXISTS',key)==0 then   \n" +
                    "  redis.call('SETEX',key,ttl,1) \n" +
                    "  return 1 \n" +
                    "else \n" +
                    "  return tonumber(redis.call('INCR',key)) \n" +
                    "end ";

    /**
     * @Description 批量获取redis缓存
     */
    public static <T> List<T> getBatch(Collection<String> keys, Class<T> tClass) {
        List<String> list = stringRedisTemplate.opsForValue().multiGet(keys);
        if (Objects.isNull(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(o -> toBeanOrNull(o, tClass)).collect(Collectors.toList());
    }

    /**
     * @param key  缓存key
     * @param time 缓存时间
     * @param unit 缓存时间单位
     * @Description 增加缓存中的值，并保证原子性
     */
    public static void inc(String key, int time, TimeUnit unit) {
        RedisScript<Long> redisScript = new DefaultRedisScript<>(LUA_INCR_EXPIRE, Long.class);
        stringRedisTemplate.execute(redisScript, Collections.singletonList(key), String.valueOf(unit.toSeconds(time)));
    }

    // --------------------------------------------------------------------------------------------

    /**
     * @param json   json字符串
     * @param tClass 转换的类型
     * @param <T>    转换的类型
     * @return 转换后的对象
     * @Description json字符串转换为对象，如果json为null则返回null
     */
    static <T> T toBeanOrNull(String json, Class<T> tClass) {
        return json == null ? null : JsonUtils.toObj(json, tClass);
    }
}
