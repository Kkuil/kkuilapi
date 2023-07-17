package com.kkuil.kkuilapi.service;

import com.kkuil.kkuilapi.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithAdminBO;
import com.kkuil.kkuilapi.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithUserBO;
import com.kkuil.kkuilapi.model.common.list.ListParams;
import com.kkuil.kkuilapi.model.common.list.ListRes;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.AddInterfaceInfo;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.UpdateInterfaceInfo;
import com.kkuil.kkuilapi.model.po.TbApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkuil.kkuilapi.model.vo.interfaceInfo.InterfaceInfoListResDataWithAdminVO;
import com.kkuil.kkuilapi.model.vo.interfaceInfo.InterfaceInfoListResDataWithUserVO;
import com.kkuil.kkuilapi.utils.ResultUtil;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;

/**
 * @author 小K
 * @Description 针对表【tb_api_info(接口信息表)】的数据库操作Service
 * @Date 2023-07-14 12:35:43
 */
public interface ITbApiInfoService extends IService<TbApiInfo> {

    /**
     * @param listParams 分页参数和除了current和pageSize之外的参数
     * @return 接口列表信息
     * @Description 管理员获取接口列表信息
     */
    ResultUtil<ListRes<InterfaceInfoListResDataWithAdminVO>> listInterfaceInfoWithAdmin(ListParams<InterfaceInfoListParamsDataWithAdminBO> listParams);

    /**
     * @param listParams 分页参数和除了current和pageSize之外的参数
     * @return 接口列表信息
     * @Description 用户获取接口列表信息
     */
    ResultUtil<ListRes<InterfaceInfoListResDataWithUserVO>> listInterfaceInfoWithUser(ListParams<InterfaceInfoListParamsDataWithUserBO> listParams);

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

    /**
     * @param id 接口ID
     * @return 是否更新成功
     * @Description 根据接口名称更新接口调用次数
     */
    ResultUtil<Boolean> updateInterfaceCountByName(int id) throws ParamsException;

}
