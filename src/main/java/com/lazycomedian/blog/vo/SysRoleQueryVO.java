package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 查询角色列表参数模型
 *
 * @author lazyComedian
 * @date 2022/12/05 10:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysRoleQueryVO extends PageVO {
    /**
     * 角色名
     */
    private String roleName;

    /**
     * 状态 0：不可用 1:可用
     */
    private Integer status;
}
