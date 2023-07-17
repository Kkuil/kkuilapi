package com.kkuil.kkuilapi;

import com.kkuil.kkuilapi.model.dto.admin.AdminLoginDTO;
import com.kkuil.kkuilapi.service.ITbAdminInfoService;
import com.kkuil.kkuilapi.utils.ResultUtil;
import com.kkuil.kkuilapicommon.exception.thrower.ParamsException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLogin {

    @Resource
    private ITbAdminInfoService adminInfoService;

    @Resource
    private HttpServletResponse response;

    @Test
    void testLogin() throws ParamsException {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
        adminLoginDTO.setAccount("Kkuil");
        adminLoginDTO.setPassword("123456");
        ResultUtil<Boolean> login = adminInfoService.login(adminLoginDTO, response);
    }
}
