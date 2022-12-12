package com.lazycomedian.blog.controller;

import com.lazycomedian.blog.dto.LoginDTO;
import com.lazycomedian.blog.service.LoginService;
import com.lazycomedian.blog.vo.LoginVO;
import com.lazycomedian.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户登录模块
 *
 * @author lazyComedian
 * @date 2022/12/03 17:40
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param user 用户登录参数
     */
    @PostMapping("/login")
    public ResultVO<LoginDTO> login(@Valid @RequestBody LoginVO user) {
        return ResultVO.success(loginService.login(user));
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public ResultVO<Boolean> logout() {
        return ResultVO.success(loginService.logout());
    }
}
