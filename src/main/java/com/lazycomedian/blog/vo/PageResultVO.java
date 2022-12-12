package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结构模型
 *
 * @author lazyComedian
 * @date 2022/12/05 11:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultVO<T> {
    /**
     * 分页列表
     */
    private List<T> list;

    /**
     * 条目总数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Long current;

    /**
     * 每页条数
     */
    private Long pageSize;
}
