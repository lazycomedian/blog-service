package com.lazycomedian.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户菜单模型
 *
 * @author lazyComedian
 * @date 2022/12/31 14:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysMenuDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
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
    private Integer sort;


    /**
     * 状态 0:不可用 1:可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 子菜单列表
     */
    private List<SysMenuDTO> children;
}
