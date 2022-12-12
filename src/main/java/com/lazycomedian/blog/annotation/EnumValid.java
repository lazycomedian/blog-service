package com.lazycomedian.blog.annotation;


import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * validation校验枚举
 *
 * @author lazyComedian
 * @date 2022/6/29 09:35
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EnumValid {
    /**
     * 枚举的类型
     */
    Class<?> value();

    /**
     * 错误消息
     */
    String message() default "枚举类型的值不正确";

    /**
     * 获取枚举值的方法名称
     *
     * @default getCode
     */
    String keyword() default "getCode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
