package com.kkuil.kkuilapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkuil.kkuilapi.mapper.TbApiInfoMapper;
import com.kkuil.kkuilapi.service.ITbApiInfoService;
import com.kkuil.kkuilapicommon.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithAdminBO;
import com.kkuil.kkuilapicommon.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithUserBO;
import com.kkuil.kkuilapicommon.model.dto.interfaceInfo.AddInterfaceInfo;
import com.kkuil.kkuilapicommon.model.dto.interfaceInfo.UpdateInterfaceInfo;
import com.kkuil.kkuilapicommon.model.list.ListParams;
import com.kkuil.kkuilapicommon.model.list.ListRes;
import com.kkuil.kkuilapicommon.model.po.TbApiInfo;
import com.kkuil.kkuilapicommon.model.vo.interfaceInfo.InterfaceInfoListResDataWithAdminVO;
import com.kkuil.kkuilapicommon.model.vo.interfaceInfo.InterfaceInfoListResDataWithUserVO;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import com.kkuil.kkuilapicommon.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 小K
 * @Description 针对表【tb_api_info(接口信息表)】的数据库操作Service实现
 * @Date 2023-07-14 12:35:43
 */
@Service
public class TbApiInfoServiceImpl extends ServiceImpl<TbApiInfoMapper, TbApiInfo> implements ITbApiInfoService {

    @Autowired
    private TbApiInfoMapper tbApiInfoMapper;

    @Value("${gateway.host}")
    private String GATEWAY_HOST;


    /**
     * @param listParams 分页参数和除了current和pageSize之外的参数
     * @return 接口列表信息
     * @Description 管理员获取接口列表信息
     */
    @Override
    public ResultUtil<ListRes<InterfaceInfoListResDataWithAdminVO>> listInterfaceInfoWithAdmin(ListParams<InterfaceInfoListParamsDataWithAdminBO> listParams) {
        Integer current = listParams.getCurrent();
        Integer pageSize = listParams.getPageSize();
        InterfaceInfoListParamsDataWithAdminBO params = listParams.getParams();
        // 获取请求列表
        List<InterfaceInfoListResDataWithAdminVO> list = tbApiInfoMapper.listInterfaceInfoWithAdminLimit(
                (current - 1) * pageSize,
                pageSize,
                params.getApiName()
        );
        // 获取总条数
        int total = tbApiInfoMapper.listInterfaceInfoWithAdminNotLimit(
                params.getApiName()
        );
        ListRes<InterfaceInfoListResDataWithAdminVO> listInfo = new ListRes<>(
                current,
                pageSize,
                total,
                list.stream().peek(item -> item.setApiUrl(GATEWAY_HOST + item.getApiUrl())).collect(java.util.stream.Collectors.toList()
                ));
        return ResultUtil.success(listInfo);
    }

    /**
     * @param listParams 分页参数和除了current和pageSize之外的参数
     * @return 接口列表信息
     * @Description 用户获取接口列表信息
     */
    @Override
    public ResultUtil<ListRes<InterfaceInfoListResDataWithUserVO>> listInterfaceInfoWithUser(ListParams<InterfaceInfoListParamsDataWithUserBO> listParams) {
        Integer current = listParams.getCurrent();
        Integer pageSize = listParams.getPageSize();
        InterfaceInfoListParamsDataWithUserBO params = listParams.getParams();
        // 获取请求列表
        List<InterfaceInfoListResDataWithUserVO> list = tbApiInfoMapper.listInterfaceInfoWithUserLimit(
                (current - 1) * pageSize,
                pageSize,
                params.getApiName()
        );
        // 获取总条数
        int total = tbApiInfoMapper.listInterfaceInfoWithUserNotLimit(
                params.getApiName()
        );
        ListRes<InterfaceInfoListResDataWithUserVO> listInfo = new ListRes<>(
                current,
                pageSize,
                total,
                list.stream().peek(item -> item.setApiUrl(GATEWAY_HOST + item.getApiUrl())).collect(java.util.stream.Collectors.toList()
                ));
        return ResultUtil.success(listInfo);
    }

    /**
     * @param interfaceInfo 接口信息
     * @return 是否保存成功
     * @Description 保存接口信息
     */
    @Override
    public ResultUtil<Boolean> saveInterfaceInfoWithAdmin(AddInterfaceInfo interfaceInfo) throws ParamsException {
        if (ObjectUtil.isAllEmpty(
                interfaceInfo.getApiName(),
                interfaceInfo.getApiDesc(),
                interfaceInfo.getApiUrl(),
                interfaceInfo.getApiMethod(),
                interfaceInfo.getApiParam(),
                interfaceInfo.getApiStatus(),
                interfaceInfo.getApiReqExample(),
                interfaceInfo.getApiResExample()
        )) {
            throw new ParamsException("参数不能为空");
        }

        TbApiInfo tbApiInfo = BeanUtil.copyProperties(interfaceInfo, TbApiInfo.class);

        int isInsert = tbApiInfoMapper.insert(tbApiInfo);
        if (isInsert == 1) {
            return ResultUtil.success(true);
        } else {
            throw new ParamsException("保存接口信息失败");
        }
    }

    /**
     * @param id 接口id
     * @return 是否删除成功
     * @Description 删除接口信息
     */
    @Override
    public ResultUtil<Boolean> deleteInterfaceInfoWithAdmin(int id) throws ParamsException {
        if (StringUtils.isEmpty(String.valueOf(id))) {
            throw new ParamsException("参数不能为空");
        }
        int isDelete = tbApiInfoMapper.deleteById(id);
        if (isDelete == 1) {
            return ResultUtil.success(true);
        } else {
            throw new ParamsException("删除接口信息失败");
        }
    }

    /**
     * @param interfaceInfo 接口信息
     * @return 是否更新成功
     * @Description 更新接口信息
     */
    @Override
    public ResultUtil<Boolean> updateInterfaceInfoWithAdmin(UpdateInterfaceInfo interfaceInfo) throws ParamsException {
        if (StringUtils.isEmpty(interfaceInfo.getId())) {
            throw new ParamsException("参数不能为空");
        }

        TbApiInfo tbApiInfo = BeanUtil.copyProperties(interfaceInfo, TbApiInfo.class);

        int isUpdate = tbApiInfoMapper.updateById(tbApiInfo);
        if (isUpdate == 1) {
            return ResultUtil.success(true);
        } else {
            throw new ParamsException("更新接口信息失败");
        }
    }

    /**
     * @param id 接口id
     * @return 接口信息
     * @Description 获取接口信息
     */
    @Override
    public ResultUtil<TbApiInfo> getInterfaceInfoWithAdmin(int id) {
        TbApiInfo tbApiInfo = tbApiInfoMapper.selectById(id);
        tbApiInfo.setApiUrl(GATEWAY_HOST + tbApiInfo.getApiUrl());
        return ResultUtil.success(tbApiInfo);
    }

    /**
     * @param id 接口id
     * @return 是否更新成功
     * @Description 更新接口调用次数
     */
    @Override
    public ResultUtil<Boolean> updateInterfaceCountByName(int id) throws ParamsException {
        UpdateWrapper<TbApiInfo> apiInfoUpdateWrapper = new UpdateWrapper<>();
        apiInfoUpdateWrapper
                .eq("id", id)
                .setSql("api_count = api_count + 1");
        boolean isUpdate = this.update(apiInfoUpdateWrapper);
        if (isUpdate) {
            return ResultUtil.success(true);
        } else {
            throw new ParamsException("更新接口信息失败");
        }
    }
}
