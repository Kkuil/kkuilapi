package com.kkuil.kkuilapiinterface.controller.random;

import com.kkuil.kkuilapicommon.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小K
 * @Date 2023/07/16 16:30
 * @Description 生成随机图片
 */
@Slf4j
@RestController
public class RandomImageController {

    /**
     * 随机图片前缀
     */
    public static final String IMAGE_URL_PREFIX = "https://kkuil-api.oss-cn-beijing.aliyuncs.com/resources/images/spider-man/spiderman-";

    /**
     * @param type 图片类型
     * @Description 生成随机图片
     */
    @GetMapping("/image")
    public ResultUtil<String> getRandomImage(@RequestParam String type) {
        // 1. 生成随机数（1-18）
        int random = (int) (Math.random() * 18 + 1);
        // 2. 拼接图片地址
        String url = IMAGE_URL_PREFIX + random + ".jpg";
        return ResultUtil.success(url);
    }
}
