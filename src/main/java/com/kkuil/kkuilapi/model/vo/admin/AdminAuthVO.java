package com.kkuil.kkuilapi.model.vo.admin;

import lombok.Data;

/**
 * @Author 小K
 * @Date 2023/7/16 9:00
 * @Description 管理员校验返回信息
 */
@Data
public class AdminAuthVO {
    /**
     * 管理员ID
     */
    private Integer id;

    /**
     * 管理员账号
     */
    private String account;
}
