package com.kkuil.kkuilapi.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 管理员信息表
 *
 * @Author 小K
 * @TableName tb_admin_info
 */
@TableName(value = "tb_admin_info")
@Data
public class TbAdminInfo implements Serializable {
    /**
     * 管理员ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @SchemaProperty(name = "id")
    private Object id;

    /**
     * 管理员账号
     */
    @TableField(value = "account")
    @SchemaProperty(name = "account")
    private String account;

    /**
     * 管理员密码
     */
    @TableField(value = "password")
    @SchemaProperty(name = "password")
    private String password;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}