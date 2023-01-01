package com.lazycomedian.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lazyComedian
 * @date 2022/12/31 13:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVO {
    /**
     * 菜单id
     */
    private Integer id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 路由
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    @NotNull(message = "菜单排序不能为空")
    private Integer order;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     * 状态 0:不可用 1:可用
     */
    @NotNull(message = "菜单状态不能为空")
    private Integer status;
}
