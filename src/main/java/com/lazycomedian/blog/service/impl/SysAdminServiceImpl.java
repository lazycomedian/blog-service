package com.lazycomedian.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazycomedian.blog.dto.SysAdminDTO;
import com.lazycomedian.blog.entity.SysAdminEntity;
import com.lazycomedian.blog.mapper.SysAdminMapper;
import com.lazycomedian.blog.service.SysAdminService;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.SysAdminQueryVO;
import com.lazycomedian.blog.vo.SysAdminVO;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户管理业务实现
 *
 * @author lazyComedian
 * @date 2022/12/05 23:53
 **/
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdminEntity> implements SysAdminService {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Override
    public List<SysAdminDTO> findAll() {
        return transformList(this.list());
    }

    @Override
    public PageResultVO<SysAdminDTO> queryList(SysAdminQueryVO adminQueryVO) {
        final Page<SysAdminEntity> page = new Page<>(adminQueryVO.getCurrent(), adminQueryVO.getPageSize());

        final LambdaQueryWrapper<SysAdminEntity> queryWrapper = new LambdaQueryWrapper<SysAdminEntity>()
                .eq(Objects.nonNull(adminQueryVO.getStatus()), SysAdminEntity::getStatus, adminQueryVO.getStatus())
                .like(Strings.hasText(adminQueryVO.getNickname()), SysAdminEntity::getNickname, adminQueryVO.getNickname());

        final Page<SysAdminEntity> selectPage = sysAdminMapper.selectPage(page, queryWrapper);

        return new PageResultVO<>(transformList(selectPage.getRecords()),
                selectPage.getTotal(), selectPage.getCurrent(), selectPage.getSize());
    }

    @Override
    public Boolean saveOrUpdateUser(SysAdminVO adminVO) {

        return this.saveOrUpdate(SysAdminEntity.builder()
                .id(adminVO.getId()).status(adminVO.getStatus()).username(adminVO.getUsername())
                .build());
    }

    private List<SysAdminDTO> transformList(List<SysAdminEntity> userEntities) {
        return userEntities.stream().map(item -> SysAdminDTO.builder()
                .id(item.getId()).nickname(item.getNickname()).status(item.getStatus())
                .username(item.getUsername()).createTime(item.getCreateTime())
                .build()
        ).collect(Collectors.toList());
    }
}
