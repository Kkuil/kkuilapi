package com.kkuil.kkuilapi.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 接口信息表
 *
 * @TableName tb_api_info
 * @Author 小K
 * @Date 2023-07-14 15:00
 * @Description 接口信息表
 */
@TableName(value = "tb_api_info")
@Data
public class TbApiInfo implements Serializable {
    /**
     * 接口ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @SchemaProperty(name = "接口ID")
    private Integer id;

    /**
     * 接口名称
     */
    @TableField(value = "api_name")
    @SchemaProperty(name = "接口名称")
    private String apiName;

    /**
     * 接口描述
     */
    @TableField(value = "api_desc")
    @SchemaProperty(name = "接口描述")
    private String apiDesc;

    /**
     * 接口地址
     */
    @TableField(value = "api_url")
    @SchemaProperty(name = "接口地址")
    private String apiUrl;

    /**
     * 请求方法
     */
    @TableField(value = "api_method")
    @SchemaProperty(name = "请求方法")
    private String apiMethod;

    /**
     * 请求参数
     */
    @TableField(value = "api_param")
    @SchemaProperty(name = "请求方法")
    private String apiParam;

    /**
     * 接口状态
     */
    @TableField(value = "api_status")
    @SchemaProperty(name = "接口状态")
    private String apiStatus;

    /**
     * 请求示例
     */
    @TableField(value = "api_req_example")
    @SchemaProperty(name = "请求示例")
    private String apiReqExample;

    /**
     * 响应示例
     */
    @TableField(value = "api_res_example")
    @SchemaProperty(name = "响应示例")
    private String apiResExample;

    /**
     * 接口创建时间
     */
    @TableField(value = "api_create_time")
    @SchemaProperty(name = "接口创建时间")
    private Date apiCreateTime;

    /**
     * 接口更新时间
     */
    @TableField(value = "api_modify_time")
    @SchemaProperty(name = "接口更新时间")
    private Date apiModifyTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}