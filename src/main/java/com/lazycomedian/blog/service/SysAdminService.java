package com.lazycomedian.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lazycomedian.blog.dto.SysAdminDTO;
import com.lazycomedian.blog.entity.SysAdminEntity;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.QueryVO;
import com.lazycomedian.blog.vo.SysAdminVO;

import java.util.List;

/**
 * 管理员业务
 *
 * @author lazyComedian
 * @date 2022/12/05 23:51
 **/
public interface SysAdminService extends IService<SysAdminEntity> {

    /**
     * 获取所有管理员
     */
    List<SysAdminDTO> findAll();

    /**
     * 条件查询管理员列表
     *
     * @param queryVO 查询参数
     */
    PageResultVO<SysAdminDTO> queryList(QueryVO queryVO);

    /**
     * 新增或修改管理员
     *
     * @param adminVO 修改的参数
     */
    Boolean saveOrUpdateUser(SysAdminVO adminVO);
}
