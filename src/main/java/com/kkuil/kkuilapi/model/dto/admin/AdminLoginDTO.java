package com.kkuil.kkuilapi.model.dto.admin;

import lombok.Data;

/**
 * @Author 小K
 * @Date 2021/7/16 8:00
 * @Description 管理员登录所需参数
 */
@Data
public class AdminLoginDTO {
    /**
     * 管理员账号
     */
    private String account;

    /**
     * 管理员密码
     */
    private String password;
}
