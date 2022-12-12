package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 管理员添加/修改模型
 *
 * @author lazyComedian
 * @date 2022/12/06 11:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAdminVO {
    /**
     * 管理员id
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 状态
     */
    @NotNull(message = "角色状态不能为空")
    private Integer status;
}
