package com.kkuil.kkuilapi.exception.thrower;

import com.kkuil.kkuilapicommon.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @Description 频率控制异常
 * @Author 小K
 * @Date 2023/07/16 12:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FrequencyControlException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer code;

    /**
     * 错误信息
     */
    protected String message;

    public FrequencyControlException() {
        super();
    }

    public FrequencyControlException(String message) {
        super(message);
        this.message = message;
    }

    public FrequencyControlException(ErrorCode error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
