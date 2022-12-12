package com.lazycomedian.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lazycomedian.blog.entity.SysAdminEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员
 *
 * @author lazyComedian
 * @date 2022/12/05 00:02
 **/
@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdminEntity> {
}
