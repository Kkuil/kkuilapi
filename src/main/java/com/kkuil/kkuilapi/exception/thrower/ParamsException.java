package com.kkuil.kkuilapi.exception.thrower;

import com.kkuil.kkuilapi.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 小K
 * @Date 2023/7/14 18:00
 * @Description 参数异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParamsException extends Exception {
    private ErrorCode code;

    public ParamsException(String message) {
        super(message);
        setCode(ErrorCode.PARAMS_ERROR);
    }
}