package com.lazycomedian.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lazycomedian.blog.entity.SysAdminRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员与角色关系
 *
 * @author lazyComedian
 * @date 2022/12/06 16:34
 **/
@Mapper
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRoleEntity> {
}
