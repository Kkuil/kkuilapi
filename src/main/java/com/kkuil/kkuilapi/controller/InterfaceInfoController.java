package com.kkuil.kkuilapi.controller;

import com.kkuil.kkuilapi.anotation.AuthAdmin;
import com.kkuil.kkuilapi.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithAdminBO;
import com.kkuil.kkuilapi.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithUserBO;
import com.kkuil.kkuilapi.model.common.list.ListParams;
import com.kkuil.kkuilapi.model.common.list.ListRes;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.AddInterfaceInfo;
import com.kkuil.kkuilapi.model.dto.interfaceInfo.UpdateInterfaceInfo;
import com.kkuil.kkuilapi.model.po.TbApiInfo;
import com.kkuil.kkuilapi.model.vo.interfaceInfo.InterfaceInfoListResDataWithAdminVO;
import com.kkuil.kkuilapi.model.vo.interfaceInfo.InterfaceInfoListResDataWithUserVO;
import com.kkuil.kkuilapi.service.ITbApiInfoService;
import com.kkuil.kkuilapi.utils.ResultUtil;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "接口信息", description = "接口信息CRUD")
public class InterfaceInfoController {

    @Resource
    private ITbApiInfoService tbApiInfoService;

    /**
     * @param current  当前页
     * @param pageSize 每页条数
     * @param apiName  接口名称
     * @return ResultUtil<ListRes < InterfaceInfoListResDataWithAdminVO>>
     * @Description 获取接口列表信息
     */
    @GetMapping("interface")
    @Operation(summary = "管理员获取接口列表信息")
    @AuthAdmin
    public ResultUtil<ListRes<InterfaceInfoListResDataWithAdminVO>> listInterfaceInfoWithAdmin(int current, int pageSize, String apiName) {
        InterfaceInfoListParamsDataWithAdminBO params = new InterfaceInfoListParamsDataWithAdminBO(apiName);
        ListParams<InterfaceInfoListParamsDataWithAdminBO> listParams = new ListParams<>(current, pageSize, params);
        return tbApiInfoService.listInterfaceInfoWithAdmin(listParams);
    }

    /**
     * @param current  当前页
     * @param pageSize 每页条数
     * @param apiName  接口名称
     * @return ResultUtil<ListRes < InterfaceInfoListResDataWithAdminVO>>
     * @Description 获取接口列表信息
     */
    @GetMapping("interface-user")
    @Operation(summary = "用户获取接口列表信息")
    public ResultUtil<ListRes<InterfaceInfoListResDataWithUserVO>> listInterfaceInfoWithUser(int current, int pageSize, String apiName) {
        InterfaceInfoListParamsDataWithUserBO params = new InterfaceInfoListParamsDataWithUserBO(apiName);
        ListParams<InterfaceInfoListParamsDataWithUserBO> listParams = new ListParams<>(current, pageSize, params);
        return tbApiInfoService.listInterfaceInfoWithUser(listParams);
    }

    /**
     * @param interfaceInfo 接口信息
     * @return 是否添加成功
     * @Description 添加接口信息
     */
    @PostMapping("interface")
    @Operation(summary = "添加接口信息")
    @AuthAdmin
    public ResultUtil<Boolean> saveInterfaceInfoWithAdmin(@RequestBody AddInterfaceInfo interfaceInfo) throws ParamsException {
        return tbApiInfoService.saveInterfaceInfoWithAdmin(interfaceInfo);
    }

    /**
     * @param id 接口id
     * @return 是否删除成功
     * @Description 删除接口信息
     */
    @DeleteMapping("interface/{id}")
    @Operation(summary = "删除接口信息")
    @AuthAdmin
    @Parameters({
            @Parameter(name = "id", description = "接口id", in = ParameterIn.PATH),
    })
    public ResultUtil<Boolean> deleteInterfaceInfoWithAdmin(@PathVariable int id) throws ParamsException {
        return tbApiInfoService.deleteInterfaceInfoWithAdmin(id);
    }

    /**
     * @param interfaceInfo 接口信息
     * @return 是否更新成功
     * @Description 更新接口信息
     */
    @PutMapping("interface")
    @Operation(summary = "更新接口信息")
    @AuthAdmin
    public ResultUtil<Boolean> updateInterfaceInfoWithAdmin(UpdateInterfaceInfo interfaceInfo) throws ParamsException {
        return tbApiInfoService.updateInterfaceInfoWithAdmin(interfaceInfo);
    }

    /**
     * @param id 接口id
     * @return 接口信息
     * @Description 获取接口信息
     */
    @GetMapping("interface/{id}")
    @Operation(summary = "获取接口信息")
    @AuthAdmin
    @Parameters({
            @Parameter(name = "id", description = "接口id", in = ParameterIn.PATH),
    })
    public ResultUtil<TbApiInfo> getInterfaceInfoWithAdmin(@PathVariable int id) throws ParamsException {
        return tbApiInfoService.getInterfaceInfoWithAdmin(id);
    }
}
