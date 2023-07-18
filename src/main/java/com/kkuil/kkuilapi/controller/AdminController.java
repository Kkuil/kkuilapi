package com.kkuil.kkuilapi.controller;

import com.kkuil.kkuilapi.model.dto.admin.AdminLoginDTO;
import com.kkuil.kkuilapi.model.vo.admin.AdminAuthVO;
import com.kkuil.kkuilapi.service.ITbAdminInfoService;
import com.kkuil.kkuilapicommon.exception.thrower.ForbiddenException;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import com.kkuil.kkuilapicommon.utils.ResultUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小K
 * @Date 2023/7/16 8:00
 * @Description 管理员操作
 */
@RestController
@Slf4j
@Tag(name = "管理员", description = "管理员操作")
public class AdminController {

    @Resource
    private ITbAdminInfoService adminInfoService;

    /**
     * @param adminLoginDTO 管理员登录信息
     * @param response 请求信息
     * @return 是否登录成功
     * @Description 管理员登录
     */
    @PostMapping("admin/login")
    @Parameter(name = "adminLoginDTO", description = "管理员登录信息", required = true)
    public ResultUtil<Boolean> login(@RequestBody AdminLoginDTO adminLoginDTO, HttpServletResponse response) throws ParamsException {
        return adminInfoService.login(adminLoginDTO, response);
    }

    /**
     * @param request HttpServletRequest
     * @return Result
     * @Description 管理员校验端口
     */
    @PostMapping("admin/auth")
    public ResultUtil<AdminAuthVO> auth(HttpServletRequest request) throws ForbiddenException {
        return adminInfoService.auth(request);
    }
}
