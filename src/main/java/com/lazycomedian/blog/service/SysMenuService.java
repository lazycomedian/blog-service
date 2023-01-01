package com.lazycomedian.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lazycomedian.blog.dto.SysMenuDTO;
import com.lazycomedian.blog.entity.SysMenuEntity;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.QueryVO;
import com.lazycomedian.blog.vo.SysMenuVO;

import java.util.List;

/**
 * 菜单管理服务
 *
 * @author lazyComedian
 * @date 2022/12/31 10:48
 **/
public interface SysMenuService extends IService<SysMenuEntity> {
    List<SysMenuDTO> findAll();

    /**
     * 条件查询菜单列表
     *
     * @param queryVO 查询条件
     */
    PageResultVO<SysMenuEntity> queryList(QueryVO queryVO);

    /**
     * 添加或修改菜单
     *
     * @param sysMenuVO 添加/修改的入参
     */
    Boolean saveOrUpdateMenu(SysMenuVO sysMenuVO);
}
