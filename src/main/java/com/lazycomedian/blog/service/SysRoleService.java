package com.lazycomedian.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lazycomedian.blog.entity.SysRoleEntity;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.SysRoleQueryVO;
import com.lazycomedian.blog.vo.SysRoleVO;

/**
 * 角色管理业务
 *
 * @author lazyComedian
 * @date 2022/12/05 00:03
 **/
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 分页查询列表
     *
     * @param roleQueryVO 查询参数
     */
    PageResultVO<SysRoleEntity> queryList(SysRoleQueryVO roleQueryVO);


    /**
     * 添加或修改角色
     *
     * @param roleVO 添加/修改角色的参数
     */
    Boolean saveOrUpdateRole(SysRoleVO roleVO);
}
