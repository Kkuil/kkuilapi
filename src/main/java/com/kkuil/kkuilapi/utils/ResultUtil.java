package com.kkuil.kkuilapi.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Author 小K
 * @Date 2023/7/14 14:30
 * @Description 统一返回结果
 */
@Data
public class ResultUtil<DataType> {
    /**
     * 状态码
     */
    private HttpStatus code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private DataType data;

    /**
     * 返回成功
     *
     * @param data       DataType 返回数据
     * @param <DataType> 返回数据类型
     * @return ResultUtil<DataType>
     */
    public static <DataType> ResultUtil<DataType> success(DataType data) {
        ResultUtil<DataType> resultUtil = new ResultUtil<>();
        resultUtil.setCode(HttpStatus.OK);
        resultUtil.setMessage("success");
        resultUtil.setData(data);
        return resultUtil;
    }

    /**
     * 返回成功
     *
     * @param msg        String 返回信息
     * @param data       DataType 返回数据
     * @param <DataType> 返回数据类型
     * @return ResultUtil<DataType>
     */
    public static <DataType> ResultUtil<DataType> success(String msg, DataType data) {
        ResultUtil<DataType> resultUtil = new ResultUtil<>();
        resultUtil.setCode(HttpStatus.OK);
        resultUtil.setMessage(msg);
        resultUtil.setData(data);
        return resultUtil;
    }

    /**
     * 返回成功
     *
     * @param code       Integer 状态码
     * @param msg        String 返回信息
     * @param data       DataType 返回数据
     * @param <DataType> 返回数据类型
     * @return ResultUtil<DataType>
     */
    public static <DataType> ResultUtil<DataType> success(HttpStatus code, String msg, DataType data) {
        ResultUtil<DataType> resultUtil = new ResultUtil<>();
        resultUtil.setCode(code);
        resultUtil.setMessage(msg);
        resultUtil.setData(data);
        return resultUtil;
    }

    /**
     * 返回失败
     *
     * @param code       Integer 状态码
     * @param msg        String 返回信息
     * @param <DataType> 返回数据类型
     * @return ResultUtil<DataType>
     */
    public static <DataType> ResultUtil<DataType> fail(HttpStatus code, String msg) {
        ResultUtil<DataType> resultUtil = new ResultUtil<>();
        resultUtil.setCode(code);
        resultUtil.setMessage(msg);
        return resultUtil;
    }

    /**
     * 重定向
     *
     * @param msg String 重定向地址
     * @return ResultUtil<DataType>
     */
    public static <DataType> ResultUtil<DataType> redirect(String msg) {
        ResultUtil<DataType> resultUtil = new ResultUtil<>();
        resultUtil.setCode(HttpStatus.PERMANENT_REDIRECT);
        resultUtil.setMessage(msg);
        return resultUtil;
    }
}
