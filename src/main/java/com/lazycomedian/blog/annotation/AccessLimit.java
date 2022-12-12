package com.lazycomedian.blog.annotation;

import java.lang.annotation.*;

/**
 * redis接口限流
 *
 * @author lazyComedian
 * @date 2022/12/05 20:14
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 单位时间（秒）
     *
     * @return int
     */
    int seconds();

    /**
     * 单位时间最大请求次数
     *
     * @return int
     */
    int maxCount();
}
