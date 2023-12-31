package com.kkuil.kkuilapicommon.exception.thrower;

import com.kkuil.kkuilapicommon.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 小K
 * @Date 2023/7/14 18:00
 * @Description 未找到异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends Exception {
    private ErrorCode code;

    public SystemException(String message) {
        super(message);
        setCode(ErrorCode.SYSTEM_ERROR);
    }
}
