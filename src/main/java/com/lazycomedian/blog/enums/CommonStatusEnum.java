package com.lazycomedian.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态
 *
 * @author lazyComedian
 * @date 2022/6/27 00:11
 **/
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {
    /**
     * 不可用
     */
    DISABLED(0),
    /**
     * 可用
     */
    AVAILABLE(1);

    private final Integer status;
}
