package com.kkuil.kkuilapigateway.common;

/**
 * @Author 小K
 * @Date 2023/7/14 19:00
 * @Description 错误状态码
 */
public enum ErrorCode {

    /**
     * 触发频控
     */
    FREQUENCY_ERROR(405, "访问速度太快了，请稍后再试"),
    /**
     * 禁止访问
     */
    FORBIDDEN_ERROR(403, "禁止访问"),
    /**
     * 服务器内部异常
     */
    SYSTEM_ERROR(500, "服务器内部异常");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
