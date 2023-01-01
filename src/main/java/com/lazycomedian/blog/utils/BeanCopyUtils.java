package com.lazycomedian.blog.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 映射对象或集合成员工具类
 *
 * @author lazyComedian
 * @date 2022/12/31 11:10
 **/
public class BeanCopyUtils {
    /**
     * 映射对象
     *
     * @param source   源数据对象
     * @param template 需要构造的数据结构对象模板
     * @return 整合源数据的映射对象结构
     */
    public static <T> T copyObject(Object source, Class<T> template) {
        T result = null;
        try {
            result = template.newInstance();
            if (Objects.nonNull(source)) {
                BeanUtils.copyProperties(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 映射集合
     *
     * @param source   源数据对象集合
     * @param template 需要构造的数据结构对象模板
     * @return 整合源数据的映射对象结构集合
     */
    public static <T, S> List<T> copyList(List<S> source, Class<T> template) {
        List<T> list = new ArrayList<>();
        if (Objects.nonNull(source) && source.size() > 0) {
            for (Object obj : source) {
                list.add(BeanCopyUtils.copyObject(obj, template));
            }
        }
        return list;
    }

}
