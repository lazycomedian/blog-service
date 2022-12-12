package com.lazycomedian.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazycomedian.blog.entity.SysRoleEntity;
import com.lazycomedian.blog.exception.BizException;
import com.lazycomedian.blog.mapper.SysRoleMapper;
import com.lazycomedian.blog.service.SysRoleService;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.SysRoleQueryVO;
import com.lazycomedian.blog.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 角色管理业务实现
 *
 * @author lazyComedian
 * @date 2022/12/05 00:05
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageResultVO<SysRoleEntity> queryList(SysRoleQueryVO roleQueryVO) {
        Page<SysRoleEntity> page = new Page<>(roleQueryVO.getCurrent(), roleQueryVO.getPageSize());
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<SysRoleEntity>()
                .eq(Objects.nonNull(roleQueryVO.getStatus()), SysRoleEntity::getStatus, roleQueryVO.getStatus())
                .like(Objects.nonNull(roleQueryVO.getRoleName()), SysRoleEntity::getRoleName, roleQueryVO.getRoleName());

        final Page<SysRoleEntity> selectPage = sysRoleMapper.selectPage(page, queryWrapper);
        return new PageResultVO<>(selectPage.getRecords(), selectPage.getTotal(), selectPage.getCurrent(), selectPage.getSize());
    }

    @Override
    public Boolean saveOrUpdateRole(SysRoleVO roleVO) {
        // 判断角色名重复
        final SysRoleEntity exitRole = sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRoleEntity>()
                .select(SysRoleEntity::getId)
                .eq(SysRoleEntity::getRoleName, roleVO.getRoleName())
        );

        if (Objects.nonNull(exitRole) && !exitRole.getId().equals(roleVO.getId())) {
            throw new BizException("角色名已存在");
        }

        final SysRoleEntity result = SysRoleEntity.builder()
                .id(roleVO.getId())
                .roleLabel(roleVO.getRoleLabel())
                .roleName(roleVO.getRoleName())
                .status(roleVO.getStatus()).build();

        return this.saveOrUpdate(result);
    }
}
