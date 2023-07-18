package com.kkuil.kkuilapicommon.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 接口信息表
 * @TableName tb_api_info
 */
@TableName(value ="tb_api_info")
@Data
public class TbApiInfo implements Serializable {
    /**
     * 接口ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 接口名称
     */
    @TableField(value = "api_name")
    private String apiName;

    /**
     * 接口描述
     */
    @TableField(value = "api_desc")
    private String apiDesc;

    /**
     * 接口地址
     */
    @TableField(value = "api_url")
    private String apiUrl;

    /**
     * 请求方法
     */
    @TableField(value = "api_method")
    private String apiMethod;

    /**
     * 请求参数
     */
    @TableField(value = "api_param")
    private String apiParam;

    /**
     * 接口状态
     */
    @TableField(value = "api_status")
    private String apiStatus;

    /**
     * 请求示例
     */
    @TableField(value = "api_req_example")
    private String apiReqExample;

    /**
     * 响应示例
     */
    @TableField(value = "api_res_example")
    private String apiResExample;

    /**
     * 接口创建时间
     */
    @TableField(value = "api_create_time")
    private Date apiCreateTime;

    /**
     * 接口更新时间
     */
    @TableField(value = "api_modify_time")
    private Date apiModifyTime;

    /**
     * 接口调用次数
     */
    @TableField(value = "api_count")
    private int apiCount;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}