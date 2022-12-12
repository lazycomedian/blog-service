package com.lazycomedian.blog.vo;


import com.lazycomedian.blog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 统一接口返回数据格式
 *
 * @author lazyComedian
 * @date 2022/6/26 20:09
 **/
@Getter
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;

    /**
     * 返回数据
     */
    private final T data;


    /**
     * 请求成功返回
     *
     * @author lazyComedian
     * @date 2022/6/18 15:41
     **/
    public static <T> ResultVO<T> success() {
        return new ResultVO<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getDesc(), null);
    }


    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getDesc(), data);
    }

    /**
     * 请求失败返回
     *
     * @author lazyComedian
     * @date 2022/6/18 15:39
     **/
    public static <T> ResultVO<T> failure() {
        return new ResultVO<>(StatusCodeEnum.FAILURE.getCode(), StatusCodeEnum.FAILURE.getDesc(), null);
    }

    public static <T> ResultVO<T> failure(String msg) {
        return new ResultVO<>(StatusCodeEnum.FAILURE.getCode(), msg, null);
    }

    public static <T> ResultVO<T> failure(String msg, T data) {
        return new ResultVO<>(StatusCodeEnum.FAILURE.getCode(), msg, data);
    }

    public static <T> ResultVO<T> failure(StatusCodeEnum statusCode) {
        return new ResultVO<>(statusCode.getCode(), statusCode.getDesc(), null);
    }

    public static <T> ResultVO<T> failure(StatusCodeEnum code, T data) {
        return new ResultVO<>(code.getCode(), code.getDesc(), data);
    }

    public static <T> ResultVO<T> failure(Integer code, String msg) {
        return new ResultVO<>(code, msg, null);
    }

}
