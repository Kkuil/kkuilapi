package com.kkuil.kkuilapicommon.common;

/**
 * @Author 小K
 * @Date 2023/7/14 19:00
 * @Description 错误状态码
 */
public enum ErrorCode {

    /**
     * 请求参数错误
     */
    PARAMS_ERROR(400, "请求参数错误"),
    /**
     * 未登录
     */
    UNAUTHORIZED_ERROR(401, "未登录"),
    /**
     * 请求数据不存在
     */
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    /**
     * 禁止访问
     */
    FORBIDDEN_ERROR(403, "禁止访问"),
    /**
     * 限流异常
     */
    FREQUENCY_LIMIT_ERROR(505, "你访问速度太快了，请稍后再试"),
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
