package com.lazycomedian.blog.controller;

import com.lazycomedian.blog.dto.SysMenuDTO;
import com.lazycomedian.blog.entity.SysMenuEntity;
import com.lazycomedian.blog.service.SysMenuService;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.QueryVO;
import com.lazycomedian.blog.vo.ResultVO;
import com.lazycomedian.blog.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理模块
 *
 * @author lazyComedian
 * @date 2022/12/29 17:45
 **/
@RestController
@RequestMapping("/admin/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单列表
     */
    @GetMapping
    public ResultVO<List<SysMenuDTO>> findAll() {
        return ResultVO.success(sysMenuService.findAll());
    }

    /**
     * 条件查询菜单列表
     *
     * @param queryVO 查询条件
     */
    @GetMapping("/queryList")
    public ResultVO<PageResultVO<SysMenuEntity>> queryList(QueryVO queryVO) {
        return ResultVO.success(sysMenuService.queryList(queryVO));
    }

    /**
     * 添加或删除菜单
     *
     * @param sysMenuVO 添加/修改的参数
     * @return 是否成功
     */
    @PostMapping
    public ResultVO<Boolean> saveOrUpdate(SysMenuVO sysMenuVO) {
        return ResultVO.success(sysMenuService.saveOrUpdateMenu(sysMenuVO));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public ResultVO<Boolean> remove(@PathVariable Integer id) {
        return ResultVO.success(sysMenuService.removeById(id));
    }

    /**
     * 修改状态
     *
     * @param id     菜单id
     * @param status 状态
     * @return 是否成功
     */
    @PutMapping("/{id}/{status}")
    public ResultVO<Boolean> changeStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return ResultVO.success(sysMenuService.updateById(SysMenuEntity.builder().id(id).status(status).build()));
    }

}
