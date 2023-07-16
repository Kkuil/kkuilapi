package com.kkuil.kkuilapigateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gateway")
@Slf4j
public class GatewayController {
    @GetMapping("/test")
    public String test() {
        log.info("gateway test");
        return "gateway test";
    }
}
