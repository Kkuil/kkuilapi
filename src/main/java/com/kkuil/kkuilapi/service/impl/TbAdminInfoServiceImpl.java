package com.kkuil.kkuilapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkuil.kkuilapi.constant.AdminConst;
import com.kkuil.kkuilapi.mapper.TbAdminInfoMapper;
import com.kkuil.kkuilapi.model.dto.admin.AdminLoginDTO;
import com.kkuil.kkuilapi.model.po.TbAdminInfo;
import com.kkuil.kkuilapi.model.vo.admin.AdminAuthVO;
import com.kkuil.kkuilapi.service.ITbAdminInfoService;
import com.kkuil.kkuilapicommon.exception.thrower.ForbiddenException;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import com.kkuil.kkuilapicommon.utils.JwtUtil;
import com.kkuil.kkuilapicommon.utils.ResultUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author 小K
 * @Description 针对表【tb_admin_info(管理员信息表)】的数据库操作Service实现
 * @Date 2023-07-14 16:16:03
 */
@Service
public class TbAdminInfoServiceImpl extends ServiceImpl<TbAdminInfoMapper, TbAdminInfo>
        implements ITbAdminInfoService {

    @Resource
    private TbAdminInfoMapper adminInfoMapper;

    /**
     * @param adminLoginDTO 管理员登录信息
     * @param response      响应信息
     * @return 是否登录成功
     * @Description 管理员登录
     */
    @Override
    public ResultUtil<Boolean> login(AdminLoginDTO adminLoginDTO, HttpServletResponse response) throws ParamsException {
        // 判空
        boolean isNotLegal = ObjectUtil.isAllEmpty(adminLoginDTO.getAccount(), adminLoginDTO.getPassword());
        if (isNotLegal) {
            throw new ParamsException("账号或密码不能为空");
        }
        QueryWrapper<TbAdminInfo> adminInfoQueryWrapper = new QueryWrapper<TbAdminInfo>()
                .eq("account", adminLoginDTO.getAccount())
                .eq("password", adminLoginDTO.getPassword());
        TbAdminInfo tbAdminInfo = adminInfoMapper.selectOne(adminInfoQueryWrapper);
        if (tbAdminInfo == null) {
            throw new ParamsException("账号或密码错误");
        }
        // 生成token
        String token = createToken(tbAdminInfo.getId().toString(), tbAdminInfo.getAccount(), AdminConst.ADMIN_TOKEN_SECRET);
        response.setHeader(AdminConst.ADMIN_TOKEN_KEY, token);
        return ResultUtil.success(true);
    }

    /**
     * @param request HttpServletRequest
     * @return Result
     * @Description 管理员校验
     */
    @Override
    public ResultUtil<AdminAuthVO> auth(HttpServletRequest request) throws ForbiddenException {
        // 1. 获取管理员id
        String token = request.getHeader(AdminConst.ADMIN_TOKEN_KEY);
        // 验证token是否有效
        Claims payload;
        try {
            payload = JwtUtil.parse(token, AdminConst.ADMIN_TOKEN_SECRET);
        } catch (Exception e) {
            throw new IllegalArgumentException("无效Token");
        }

        QueryWrapper<TbAdminInfo> adminInfoQueryWrapper = new QueryWrapper<>();
        adminInfoQueryWrapper
                .eq("id", payload.get("id"))
                .eq("account", payload.get("account"));
        TbAdminInfo tbAdminInfo = adminInfoMapper.selectOne(adminInfoQueryWrapper);
        if(tbAdminInfo == null){
            throw new ForbiddenException("无效Token");
        }
        AdminAuthVO adminAuthVO = new AdminAuthVO();
        BeanUtil.copyProperties(tbAdminInfo, adminAuthVO);
        return ResultUtil.success(adminAuthVO);
    }

    /**
     * @param id      管理员id
     * @param account 管理员账号
     * @param secret  密钥
     * @return token
     * @Description 生成token
     */
    public String createToken(String id, String account, String secret) {
        HashMap<String, Object> adminTokenInfoMap = new HashMap<>();
        adminTokenInfoMap.put("id", id);
        adminTokenInfoMap.put("account", account);
        return JwtUtil.create(adminTokenInfoMap, secret, AdminConst.ADMIN_TOKEN_TTL);
    }
}




