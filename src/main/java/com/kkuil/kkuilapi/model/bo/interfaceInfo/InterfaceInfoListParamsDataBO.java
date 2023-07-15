package com.kkuil.kkuilapi.model.bo.interfaceInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小K
 * @Date 2023/7/14 15:00
 * @Description 获取接口列表信息所传递的除了current和pageSize之外的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceInfoListParamsDataBO {
    /**
     * 接口名称
     */
    private String apiName;
}
