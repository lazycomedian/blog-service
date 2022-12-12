package com.lazycomedian.blog.handler;

import com.lazycomedian.blog.enums.StatusCodeEnum;
import com.lazycomedian.blog.exception.BizException;
import com.lazycomedian.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;


/**
 * 全局异常处理
 *
 * @author lazyComedian
 * @date 2021/06/11
 **/
@Slf4j
@RestControllerAdvice
public class ControllerAdviceHandler {

    /**
     * 处理服务异常
     *
     * @param e 异常对象
     */
    @ExceptionHandler(value = BizException.class)
    public ResultVO<?> catchException(BizException e) {
        log.error("<--------------- 服务异常信息：${} --------------->", e.getMessage());
        return ResultVO.failure(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<?> errorHandler(MethodArgumentNotValidException e) {
        log.error("<--------------- 参数异常信息：${} --------------->", e.getMessage());
        String errorMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        return ResultVO.failure(StatusCodeEnum.VALID_ERROR.getCode(), errorMessage);
    }

    /**
     * 处理系统异常
     *
     * @param e 异常对象
     */
    @ExceptionHandler(value = Exception.class)
    public ResultVO<?> errorHandler(Exception e) {
        e.printStackTrace();
        log.error("<--------------- 系统异常信息：${} --------------->", e.getMessage());
        return ResultVO.failure(StatusCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 未找到请求地址对应的接口
     *
     * @param e 异常对象
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultVO<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultVO.failure(StatusCodeEnum.NOT_FOUND);
    }

}
