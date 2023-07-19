package com.kkuil.kkuilapiinterface.utils.random;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Author 小K
 * @Date 2023/7/18 15:00
 * @Description 文本工具类
 */
@Slf4j(topic = "RandomTextController")
public class TextUtil {
    /**
     * @return 随机文本
     * @Description 获取随机文本
     */
    public static String getRandomText(int minLen, int maxLen) {
        // Unicode编码范围开始，表示中文字符的起始点
        int start = 0x4E00;
        // Unicode编码范围结束，表示中文字符的结束点
        int end = 0x9FA5;
        Random random = new Random();
        // 从minLen到maxLen之间的随机长度
        int length = minLen + random.nextInt(maxLen - minLen + 1);
        log.debug("随机文本长度为：{}", length);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int code = start + random.nextInt(end - start + 1);
            char randomChar = (char) code;
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
