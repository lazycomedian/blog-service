package com.lazycomedian.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 管理员数据模型
 *
 * @author lazyComedian
 * @date 2022/12/05 23:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAdminDTO {
    /**
     * 主键
     */
    private Integer id;
    
    /**
     * 管理员账号
     */
    private String username;

    /**
     * 管理员名称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态 0：不可用 1:可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
