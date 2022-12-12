package com.lazycomedian.blog.controller;

import com.lazycomedian.blog.dto.SysAdminDTO;
import com.lazycomedian.blog.entity.SysAdminEntity;
import com.lazycomedian.blog.service.SysAdminService;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.ResultVO;
import com.lazycomedian.blog.vo.SysAdminQueryVO;
import com.lazycomedian.blog.vo.SysAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.lazycomedian.blog.constant.CommonConst.SUPER_ADMIN_ID;
import static com.lazycomedian.blog.enums.StatusCodeEnum.UN_AUTHORIZED;

/**
 * 管理员列表模块
 *
 * @author lazyComedian
 * @date 2022/12/05 23:40
 **/
@RestController
@RequestMapping("/admin/sysAdmin")
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;


    /**
     * 查询所有管理员
     */
    @GetMapping
    public ResultVO<List<SysAdminDTO>> findAll() {
        return ResultVO.success(sysAdminService.findAll());
    }

    /**
     * 条件查询管理员列表
     */
    @GetMapping("/queryList")
    public ResultVO<PageResultVO<SysAdminDTO>> queryList(SysAdminQueryVO userQueryVO) {
        return ResultVO.success(sysAdminService.queryList(userQueryVO));
    }

    /**
     * 添加或修改管理员
     *
     * @param userVO 添加/修改的参数
     */
    @PostMapping
    public ResultVO<Boolean> saveOrUpdate(@Valid @RequestBody SysAdminVO userVO) {
        if (userVO.getId().equals(SUPER_ADMIN_ID)) return ResultVO.failure(UN_AUTHORIZED);
        return ResultVO.success(sysAdminService.saveOrUpdateUser(userVO));
    }

    /**
     * 删除管理员
     *
     * @param id 管理员id
     */
    @DeleteMapping("/{id}")
    public ResultVO<Boolean> remove(@PathVariable Integer id) {
        if (id.equals(SUPER_ADMIN_ID)) return ResultVO.failure(UN_AUTHORIZED);
        return ResultVO.success(sysAdminService.removeById(id));
    }

    /**
     * 修改状态
     *
     * @param id     管理员id
     * @param status 状态
     */
    @PutMapping("/{id}/{status}")
    public ResultVO<Boolean> changeStatus(@PathVariable Integer id, @PathVariable Integer status) {
        if (id.equals(SUPER_ADMIN_ID)) return ResultVO.failure(UN_AUTHORIZED);
        return ResultVO.success(sysAdminService.updateById(SysAdminEntity.builder().id(id).status(status).build()));
    }
}
