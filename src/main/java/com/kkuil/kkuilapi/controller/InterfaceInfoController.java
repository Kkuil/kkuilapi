package com.kkuil.kkuilapi.controller;

import com.kkuil.kkuilapi.exception.thrower.ParamsException;
import com.kkuil.kkuilapi.model.bo.interfaceInfo.InterfaceInfoListParamsDataBO;
import com.kkuil.kkuilapi.model.common.list.ListParams;
import com.kkuil.kkuilapi.model.common.list.ListRes;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.AddInterfaceInfo;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.UpdateInterfaceInfo;
import com.kkuil.kkuilapi.model.po.TbApiInfo;
import com.kkuil.kkuilapi.model.vo.interfaceInfo.InterfaceInfoListResDataVO;
import com.kkuil.kkuilapi.service.ITbApiInfoService;
import com.kkuil.kkuilapi.utils.ResultUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 小K
 * @Date 2023/7/14 14:00
 * @Description 接口信息CRUD
 */
@RestController
@Slf4j
public class InterfaceInfoController {

    @Resource
    private ITbApiInfoService tbApiInfoService;

    /**
     * @param current  当前页
     * @param pageSize 每页条数
     * @param apiName  接口名称
     * @return ResultUtil<ListRes < InterfaceInfoListResDataVO>>
     * @Description 获取接口列表信息
     */
    @GetMapping("interface")
//    @AuthAdmin
    public ResultUtil<ListRes<InterfaceInfoListResDataVO>> listInterfaceInfoWithAdmin(int current, int pageSize, String apiName) {
        InterfaceInfoListParamsDataBO params = new InterfaceInfoListParamsDataBO(apiName);
        ListParams<InterfaceInfoListParamsDataBO> listParams = new ListParams<>(current, pageSize, params);
        return tbApiInfoService.listInterfaceInfoWithAdmin(listParams);
    }

    /**
     * @param interfaceInfo 接口信息
     * @return 是否添加成功
     * @Description 添加接口信息
     */
    @PostMapping("interface")
//    @AuthAdmin
    public ResultUtil<Boolean> saveInterfaceInfoWithAdmin(AddInterfaceInfo interfaceInfo) throws ParamsException {
        return tbApiInfoService.saveInterfaceInfoWithAdmin(interfaceInfo);
    }

    /**
     * @param id 接口id
     * @return 是否删除成功
     * @Description 删除接口信息
     */
    @DeleteMapping("interface/{id}")
//    @AuthAdmin
    public ResultUtil<Boolean> deleteInterfaceInfoWithAdmin(@PathVariable int id) throws ParamsException {
        return tbApiInfoService.deleteInterfaceInfoWithAdmin(id);
    }

    /**
     * @param interfaceInfo 接口信息
     * @return 是否更新成功
     * @Description 更新接口信息
     */
    @PutMapping("interface")
//    @AuthAdmin
    public ResultUtil<Boolean> updateInterfaceInfoWithAdmin(UpdateInterfaceInfo interfaceInfo) throws ParamsException {
        return tbApiInfoService.updateInterfaceInfoWithAdmin(interfaceInfo);
    }

    /**
     * @param id 接口id
     * @return 接口信息
     * @Description 获取接口信息
     */
    @GetMapping("interface/{id}")
//    @AuthAdmin
    public ResultUtil<TbApiInfo> getInterfaceInfoWithAdmin(@PathVariable int id) throws ParamsException {
        return tbApiInfoService.getInterfaceInfoWithAdmin(id);
    }
}
