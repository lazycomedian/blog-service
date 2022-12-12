package com.lazycomedian.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lazycomedian.blog.enums.StatusCodeEnum;


/**
 * 业务异常
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException {

    /**
     * 错误信息
     */
    private final String message;
    /**
     * 错误码
     */
    private Integer code = StatusCodeEnum.FAILURE.getCode();

    public BizException(String message) {
        this.message = message;
    }

    public BizException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }

}
