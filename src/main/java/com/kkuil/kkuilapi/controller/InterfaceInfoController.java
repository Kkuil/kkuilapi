package com.kkuil.kkuilapi.controller;

import com.kkuil.kkuilapi.anotation.AuthAdmin;
import com.kkuil.kkuilapicommon.anotation.FrequencyControl;
import com.kkuil.kkuilapicommon.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithAdminBO;
import com.kkuil.kkuilapicommon.model.bo.interfaceInfo.InterfaceInfoListParamsDataWithUserBO;
import com.kkuil.kkuilapicommon.model.dto.interfaceInfo.AddInterfaceInfo;
import com.kkuil.kkuilapicommon.model.dto.interfaceInfo.UpdateInterfaceInfo;
import com.kkuil.kkuilapicommon.model.list.ListParams;
import com.kkuil.kkuilapicommon.model.list.ListRes;
import com.kkuil.kkuilapicommon.model.po.TbApiInfo;
import com.kkuil.kkuilapicommon.model.vo.interfaceInfo.InterfaceInfoListResDataWithAdminVO;
import com.kkuil.kkuilapicommon.model.vo.interfaceInfo.InterfaceInfoListResDataWithUserVO;
import com.kkuil.kkuilapi.service.ITbApiInfoService;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import com.kkuil.kkuilapicommon.utils.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param current  当前页
     * @param pageSize 每页条数
     * @param apiName  接口名称
     * @return ResultUtil<ListRes < InterfaceInfoListResDataWithAdminVO>>
     * @Description 获取接口列表信息
     */
    @GetMapping("interface-user")
    @Operation(summary = "用户获取接口列表信息")
    @FrequencyControl(prefixKey = "list:interface:user", time = 10, count = 5, target = FrequencyControl.Target.IP)
    @FrequencyControl(prefixKey = "list:interface:user", time = 30, count = 10, target = FrequencyControl.Target.IP)
    public ResultUtil<ListRes<InterfaceInfoListResDataWithUserVO>> listInterfaceInfoWithUser(int current, int pageSize, String apiName) {
        InterfaceInfoListParamsDataWithUserBO params = new InterfaceInfoListParamsDataWithUserBO(apiName);
        ListParams<InterfaceInfoListParamsDataWithUserBO> listParams = new ListParams<>(current, pageSize, params);
        return tbApiInfoService.listInterfaceInfoWithUser(listParams);
    }

    /**
     * @param id 接口id
     * @return 接口信息
     * @Description 获取接口信息
     */
    @GetMapping("interface/{id}")
    @Operation(summary = "获取接口信息")
    @Parameters({
            @Parameter(name = "id", description = "接口id", in = ParameterIn.PATH),
    })
    public ResultUtil<TbApiInfo> getInterfaceInfoWithAdmin(@PathVariable int id) {
        return tbApiInfoService.getInterfaceInfoWithAdmin(id);
    }

    /**
     * @return 接口总数
     * @Author 小K
     * @Description 获取总接口数
     */
    @GetMapping("/interface/total")
    @Operation(summary = "获取总接口数")
    public ResultUtil<Integer> getInterfaceTotal() {
        List<TbApiInfo> list = tbApiInfoService.list();
        return ResultUtil.success(list.size());
    }

    /**
     * @return 总调用次数
     * @Author 小K
     * @Description 获取总调用次数
     */
    @GetMapping("/interface/invoke-total")
    @Operation(summary = "获取总调用次数")
    public ResultUtil<Integer> getInterfaceInvokeTotal() {
        List<TbApiInfo> list = tbApiInfoService.list();
        int invokeCount = list.stream().mapToInt(api -> tbApiInfoService.getById(api.getId()).getApiCount()).sum();
        return ResultUtil.success(invokeCount);
    }

    /**
     * @param id 接口id
     * @return 是否更新成功
     * @Description 接口调用次数+1
     */
    @GetMapping("interface/invoke-count")
    @Operation(summary = "接口调用次数+1")
    @Parameters({
            @Parameter(name = "id", description = "接口id", in = ParameterIn.QUERY),
    })
    @AuthAdmin
    public ResultUtil<Boolean> invokeCount(int id) throws ParamsException {
        return tbApiInfoService.updateInterfaceCountByName(id);
    }
}
