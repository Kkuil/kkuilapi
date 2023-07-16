package com.kkuil.kkuilapi.service;

import com.kkuil.kkuilapi.model.dto.admin.AdminLoginDTO;
import com.kkuil.kkuilapi.model.po.TbAdminInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkuil.kkuilapi.model.vo.admin.AdminAuthVO;
import com.kkuil.kkuilapi.utils.ResultUtil;
import com.kkuil.kkuilapicommon.exception.thrower.ForbiddenException;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author 小K
 * @Description 针对表【tb_admin_info(管理员信息表)】的数据库操作Service
 * @Date 2023-07-14 16:16:03
 */
public interface ITbAdminInfoService extends IService<TbAdminInfo> {

    /**
     * @param adminLoginDTO 管理员登录信息
     * @param response 响应信息
     * @return 是否登录成功
     * @Description 管理员登录
     */
    ResultUtil<Boolean> login(AdminLoginDTO adminLoginDTO, HttpServletResponse response) throws ParamsException;

    /**
     * @param request HttpServletRequest
     * @return Result
     * @Description 管理员校验
     */
    ResultUtil<AdminAuthVO> auth(HttpServletRequest request) throws ForbiddenException;
}
