package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页查询模型
 *
 * @author lazyComedian
 * @date 2022/12/06 10:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO implements Serializable {
    /**
     * 当前页码
     */
    private Long current = 1L;

    /**
     * 每页条数
     */
    private Long pageSize = 10L;
}
