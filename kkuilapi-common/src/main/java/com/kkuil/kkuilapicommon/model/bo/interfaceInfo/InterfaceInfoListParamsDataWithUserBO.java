package com.kkuil.kkuilapicommon.model.bo.interfaceInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小K
 * @Date 2023/7/14 15:00
 * @Description 用户获取接口列表信息所传递的除了current和pageSize之外的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceInfoListParamsDataWithUserBO {
    /**
     * 接口名称
     */
    private String apiName;
}
