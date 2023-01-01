package com.lazycomedian.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazycomedian.blog.dto.SysMenuDTO;
import com.lazycomedian.blog.entity.SysMenuEntity;
import com.lazycomedian.blog.mapper.SysMenuMapper;
import com.lazycomedian.blog.service.SysMenuService;
import com.lazycomedian.blog.utils.BeanCopyUtils;
import com.lazycomedian.blog.vo.PageResultVO;
import com.lazycomedian.blog.vo.QueryVO;
import com.lazycomedian.blog.vo.SysMenuVO;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单管理服务实现
 *
 * @author lazyComedian
 * @date 2022/12/31 10:51
 **/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuDTO> findAll() {
        final List<SysMenuEntity> menuList = this.list();
        // 获取目录下的所有子菜单集合
        final Map<Integer, List<SysMenuEntity>> menuChildrenMap = getMenuChildrenMap(menuList);
        // 组装目录菜单数据
        final List<SysMenuDTO> sysMenuDTOS = getMenuCatalog(menuList).stream().map(item -> {
            final SysMenuDTO sysMenuDTO = BeanCopyUtils.copyObject(item, SysMenuDTO.class);
            // 获取目录下的菜单排序
            final List<SysMenuDTO> list = BeanCopyUtils.copyList(menuChildrenMap.get(item.getId()), SysMenuDTO.class).stream()
                    .sorted(Comparator.comparing(SysMenuDTO::getSort))
                    .collect(Collectors.toList());
            sysMenuDTO.setChildren(list);
            menuChildrenMap.remove(item.getId());
            return sysMenuDTO;
        }).sorted(Comparator.comparing(SysMenuDTO::getSort)).collect(Collectors.toList());

        // 若还有菜单未取出则拼接
        if (CollectionUtils.isNotEmpty(menuChildrenMap)) {
            final ArrayList<SysMenuEntity> childrenList = new ArrayList<>();
            menuChildrenMap.values().forEach(childrenList::addAll);

            final List<SysMenuDTO> childrenDTOList = BeanCopyUtils.copyList(childrenList, SysMenuDTO.class).stream()
                    .sorted(Comparator.comparing(SysMenuDTO::getSort))
                    .collect(Collectors.toList());
            sysMenuDTOS.addAll(childrenDTOList);
        }
        return sysMenuDTOS;
    }

    @Override
    public PageResultVO<SysMenuEntity> queryList(QueryVO queryVO) {
        final Page<SysMenuEntity> page = new Page<>(queryVO.getCurrent(), queryVO.getPageSize());
        final LambdaQueryWrapper<SysMenuEntity> queryWrapper = new LambdaQueryWrapper<SysMenuEntity>()
                .eq(Objects.nonNull(queryVO.getStatus()), SysMenuEntity::getStatus, queryVO.getStatus())
                .like(Strings.hasText(queryVO.getContent()), SysMenuEntity::getName, queryVO.getContent());

        return PageResultVO.factory(sysMenuMapper.selectPage(page, queryWrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveOrUpdateMenu(SysMenuVO sysMenuVO) {
        return this.saveOrUpdate(BeanCopyUtils.copyObject(sysMenuVO, SysMenuEntity.class));
    }

    /**
     * 获取菜单一级目录集合
     *
     * @param menuList 菜单列表
     */
    private List<SysMenuEntity> getMenuCatalog(List<SysMenuEntity> menuList) {
        return menuList.stream()
                .filter(item -> Objects.isNull(item.getPid()))
                .sorted(Comparator.comparing(SysMenuEntity::getSort))
                .collect(Collectors.toList());
    }


    /**
     * 获取以父id为键的子菜单集合
     *
     * @param menuList 菜单列表
     */
    private Map<Integer, List<SysMenuEntity>> getMenuChildrenMap(List<SysMenuEntity> menuList) {
        return menuList.stream()
                .filter(item -> Objects.nonNull(item.getPid()))
                .collect(Collectors.groupingBy(SysMenuEntity::getPid));
    }

}
