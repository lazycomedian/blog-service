package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 通用查询模型
 *
 * @author lazyComedian
 * @date 2022/12/31 15:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QueryVO extends PageVO {
    /**
     * 查询内容
     */
    private String content;

    /**
     * 状态 0:不可用 1:可用
     */
    private Integer status;
}
