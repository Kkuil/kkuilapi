package com.kkuil.kkuilapicommon.model.dto.interfaceInfo;

import lombok.Data;

/**
 * @Author 小K
 * @Date 2023/7/14 20:00
 * @Description 添加接口信息
 */
@Data
public class AddInterfaceInfo {
    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 接口描述
     */
    private String apiDesc;

    /**
     * 接口地址
     */
    private String apiUrl;

    /**
     * 请求方法
     */
    private String apiMethod;

    /**
     * 请求参数
     */
    private String apiParam;

    /**
     * 接口状态
     */
    private String apiStatus;

    /**
     * 请求示例
     */
    private String apiReqExample;

    /**
     * 响应示例
     */
    private String apiResExample;
}
