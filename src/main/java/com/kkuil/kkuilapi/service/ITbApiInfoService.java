package com.kkuil.kkuilapi.service;

import com.kkuil.kkuilapi.exception.thrower.ParamsException;
import com.kkuil.kkuilapi.model.bo.interfaceInfo.InterfaceInfoListParamsDataBO;
import com.kkuil.kkuilapi.model.common.list.ListParams;
import com.kkuil.kkuilapi.model.common.list.ListRes;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.AddInterfaceInfo;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.UpdateInterfaceInfo;
import com.kkuil.kkuilapi.model.po.TbApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkuil.kkuilapi.model.vo.interfaceInfo.InterfaceInfoListResDataVO;
import com.kkuil.kkuilapi.utils.ResultUtil;

/**
 * @author 小K
 * @Description 针对表【tb_api_info(接口信息表)】的数据库操作Service
 * @Date 2023-07-14 12:35:43
 */
public interface ITbApiInfoService extends IService<TbApiInfo> {

    /**
     * 获取接口列表信息
     *
     * @param listParams 分页参数和除了current和pageSize之外的参数
     * @return 接口列表信息
     */
    ResultUtil<ListRes<InterfaceInfoListResDataVO>> listInterfaceInfoWithAdmin(ListParams<InterfaceInfoListParamsDataBO> listParams);

    /**
     * @param interfaceInfo 接口信息
     * @return 是否保存成功
     * @Description 保存接口信息
     */
    ResultUtil<Boolean> saveInterfaceInfoWithAdmin(AddInterfaceInfo interfaceInfo) throws ParamsException;

    /**
     * @param id 接口id
     * @return 是否删除成功
     * @Description 删除接口信息
     */
    ResultUtil<Boolean> deleteInterfaceInfoWithAdmin(int id) throws ParamsException;

    /**
     * @param interfaceInfo 接口信息
     * @return 是否更新成功
     * @Description 更新接口信息
     */
    ResultUtil<Boolean> updateInterfaceInfoWithAdmin(UpdateInterfaceInfo interfaceInfo) throws ParamsException;

    /**
     * @param id 接口id
     * @return 接口信息
     * @Description 获取接口信息
     */
    ResultUtil<TbApiInfo> getInterfaceInfoWithAdmin(int id);
}
