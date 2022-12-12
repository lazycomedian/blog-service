package com.lazycomedian.blog.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 角色管理添加/更新参数模型
 *
 * @author lazyComedian
 * @date 2022/12/05 17:08
 **/
@Data
public class SysRoleVO {
    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    /**
     * 角色权限标签
     */
    private String roleLabel;

    /**
     * 角色状态
     */
    @NotNull(message = "角色状态不能为空")
    private Integer status;
}
