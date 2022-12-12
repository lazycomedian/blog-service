package com.lazycomedian.blog.dto;

import lombok.Data;

/**
 * 管理员登录返回数据模型
 *
 * @author lazyComedian
 * @date 2022/12/03 22:21
 **/
@Data
public class LoginDTO {

    /**
     * token令牌
     */
    private String token;
}
