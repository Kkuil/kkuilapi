package com.kkuil.kkuilapiinterface.controller.image;

import com.kkuil.kkuilapiinterface.service.image.RandomImageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 小K
 * @Date 2023/07/16 16:30
 * @Description 生成随机图片
 */
@RequestMapping("/image")
@Slf4j
public class RandomImageController {

    @Resource
    private RandomImageService randomImageService;



}
