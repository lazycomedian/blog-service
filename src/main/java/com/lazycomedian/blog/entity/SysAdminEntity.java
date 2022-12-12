package com.lazycomedian.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 管理员实体类
 *
 * @author lazyComedian
 * @date 2022/12/03 17:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("sys_admin")
public class SysAdminEntity extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
}
