package com.lazycomedian.blog.controller;

import com.lazycomedian.blog.entity.SysRoleEntity;
import com.lazycomedian.blog.service.SysRoleService;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.ResultVO;
import com.lazycomedian.blog.vo.SysRoleQueryVO;
import com.lazycomedian.blog.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理模块
 *
 * @author lazyComedian
 * @date 2022/12/05 00:00
 **/
@RestController
@RequestMapping("/admin/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有角色列表
     */
    @GetMapping
    public ResultVO<List<SysRoleEntity>> list() {
        return ResultVO.success(sysRoleService.list());
    }

    /**
     * 条件查询角色列表
     *
     * @param roleQuery 查询条件
     */
    @GetMapping("/queryList")
    public ResultVO<PageResultVO<SysRoleEntity>> queryList(SysRoleQueryVO roleQuery) {
        final PageResultVO<SysRoleEntity> result = sysRoleService.queryList(roleQuery);
        return ResultVO.success(result);
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    @DeleteMapping("/{id}")
    public ResultVO<Boolean> remove(@PathVariable Integer id) {
        return ResultVO.success(sysRoleService.removeById(id));
    }

    /**
     * 添加或修改角色
     *
     * @param role 添加的角色参数
     */
    @PostMapping
    public ResultVO<Boolean> saveOrUpdate(@Valid @RequestBody SysRoleVO role) {
        return ResultVO.success(sysRoleService.saveOrUpdateRole(role));
    }

    /**
     * 修改用户状态
     *
     * @param id     用户id
     * @param status 用户状态
     */
    @PutMapping("/{id}/{status}")
    public ResultVO<Boolean> changeStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return ResultVO.success(sysRoleService.updateById(SysRoleEntity.builder().id(id).status(status).build()));
    }

}
