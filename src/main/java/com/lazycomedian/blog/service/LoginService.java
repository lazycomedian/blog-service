package com.lazycomedian.blog.service;

import com.lazycomedian.blog.dto.LoginDTO;
import com.lazycomedian.blog.vo.LoginVO;

/**
 * 管理员登录
 *
 * @author lazyComedian
 * @date 2022/12/03 17:40
 **/
public interface LoginService {

    LoginDTO login(LoginVO user);

    Boolean logout();
}
