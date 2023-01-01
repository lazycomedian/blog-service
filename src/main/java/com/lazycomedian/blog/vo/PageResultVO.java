package com.lazycomedian.blog.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 响应数据分页结构模型
 *
 * @author lazyComedian
 * @date 2022/12/05 11:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResultVO<T> {
    /**
     * 列表数据
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

    /**
     * 统一构造条件查询返回结构方法
     *
     * @param selectPage 分页查询结果
     */
    public static <S> PageResultVO<S> factory(Page<S> selectPage) {
        return new PageResultVO<>(selectPage.getRecords(), selectPage.getTotal(), selectPage.getCurrent(), selectPage.getSize());
    }
}
