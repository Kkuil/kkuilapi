package com.kkuil.kkuilapi.model.common.list;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小K
 * @Date 2023/7/14 14:30
 * @Description 请求列表信息传递参数
 */
@Data
@NoArgsConstructor
public class ListParams<DataType> {
    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 除了current和pageSize之外的参数
     */
    private DataType params;

    public ListParams(int current, int pageSize, DataType params) {
        this.current = current;
        this.pageSize = pageSize;
        this.params = params;
    }

}
