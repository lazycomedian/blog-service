package com.lazycomedian.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lazycomedian.blog.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 *
 * @author lazyComedian
 * @date 2022/12/31 10:48
 **/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
}
