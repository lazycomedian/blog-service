package com.lazycomedian.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lazycomedian.blog.dto.UserDetailDTO;
import com.lazycomedian.blog.entity.SysAdminEntity;
import com.lazycomedian.blog.enums.StatusCodeEnum;
import com.lazycomedian.blog.exception.BizException;
import com.lazycomedian.blog.mapper.SysAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysAdminMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 根据用户名获取数据库中的系统用户

        LambdaQueryWrapper<SysAdminEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysAdminEntity::getUsername, username);

        final SysAdminEntity user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new BizException(StatusCodeEnum.USERNAME_NOT_EXIST);
        }

        // TODO 查询权限信息
        List<String> authList = new ArrayList<>(Arrays.asList("hello", "delgoods"));

        // 返回UserDetails
        return new UserDetailDTO(user, authList);
    }
}
