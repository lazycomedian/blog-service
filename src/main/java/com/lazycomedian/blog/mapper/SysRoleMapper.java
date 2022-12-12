package com.lazycomedian.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lazycomedian.blog.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理
 *
 * @author lazyComedian
 * @date 2022/12/05 00:02
 **/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
}
