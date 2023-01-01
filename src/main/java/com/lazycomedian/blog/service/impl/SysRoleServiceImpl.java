package com.lazycomedian.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazycomedian.blog.entity.SysRoleEntity;
import com.lazycomedian.blog.exception.BizException;
import com.lazycomedian.blog.mapper.SysRoleMapper;
import com.lazycomedian.blog.service.SysRoleService;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.QueryVO;
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
    public PageResultVO<SysRoleEntity> queryList(QueryVO queryVO) {
        Page<SysRoleEntity> page = new Page<>(queryVO.getCurrent(), queryVO.getPageSize());
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<SysRoleEntity>()
                .eq(Objects.nonNull(queryVO.getStatus()), SysRoleEntity::getStatus, queryVO.getStatus())
                .like(Objects.nonNull(queryVO.getContent()), SysRoleEntity::getRoleName, queryVO.getContent());

        return PageResultVO.factory(sysRoleMapper.selectPage(page, queryWrapper));
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
