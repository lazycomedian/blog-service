package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 管理员列表查询模型
 *
 * @author lazyComedian
 * @date 2022/12/06 10:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysAdminQueryVO extends PageVO {
    /**
     * 管理员名称
     */
    private String nickname;

    /**
     * 状态 0：不可用 1:可用
     */
    private Integer status;
}
