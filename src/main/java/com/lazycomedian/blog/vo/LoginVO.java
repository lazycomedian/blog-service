package com.lazycomedian.blog.vo;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录参数模型
 *
 * @author lazyComedian
 * @date 2022/12/03 17:40
 **/
@Getter
public class LoginVO implements Serializable {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
