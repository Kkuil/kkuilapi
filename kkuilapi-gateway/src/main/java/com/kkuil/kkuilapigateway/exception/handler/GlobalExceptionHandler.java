package com.kkuil.kkuilapigateway.exception.handler;

import com.kkuil.kkuilapigateway.exception.thrower.FrequencyControlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author 小K
 * @Date 2023/7/16 15:00
 * @Description 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @param e Exception
     * @return ResponseEntity
     * @Description 处理非法异常
     */
    @ExceptionHandler(value = FrequencyControlException.class)
    public ResponseEntity handleIllegalException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
}