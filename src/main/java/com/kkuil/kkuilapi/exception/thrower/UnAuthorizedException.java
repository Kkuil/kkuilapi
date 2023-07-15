package com.kkuil.kkuilapi.exception.thrower;

import com.kkuil.kkuilapi.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 小K
 * @Date 2023/7/14 18:00
 * @Description 未登录异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnAuthorizedException extends Exception {
    private ErrorCode code;

    public UnAuthorizedException(String message) {
        super(message);
        setCode(ErrorCode.UNAUTHORIZED_ERROR);
    }
}
