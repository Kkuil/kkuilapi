package com.kkuil.kkuilapicommon.exception.handler;

import com.kkuil.kkuilapicommon.exception.thrower.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author 小K
 * @Date 2023/7/14 18:00
 * @Description 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param e Exception
     * @return ResponseEntity
     * @Description 处理未登录异常
     */
    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity handleUnAuthorizedException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }

    /**
     * @param e Exception
     * @return ResponseEntity
     * @Description 处理未参数异常
     */
    @ExceptionHandler(value = ParamsException.class)
    public ResponseEntity handleParamsException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    /**
     * @param e Exception
     * @return ResponseEntity
     * @Description 处理404异常
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity handleNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    /**
     * @param e Exception
     * @return ResponseEntity
     * @Description 处理系统异常
     */
    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity handleSystemException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    /**
     * @param e Exception
     * @return ResponseEntity
     * @Description 处理非法异常
     */
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity handleIllegalException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
}