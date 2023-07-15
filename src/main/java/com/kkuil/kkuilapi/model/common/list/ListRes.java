package com.kkuil.kkuilapi.model.common.list;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author 小K
 * @Date 2023/7/14 15:00
 * @Description 返回列表信息
 */
@Data
@AllArgsConstructor
public class ListRes<DataType> {
    /**
     * 当前页
     */
    private Integer current;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 数据
     */
    private List<DataType> list;
}
