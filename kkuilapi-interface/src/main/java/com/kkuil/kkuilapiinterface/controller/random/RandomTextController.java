package com.kkuil.kkuilapiinterface.controller.random;

import com.kkuil.kkuilapicommon.utils.ResultUtil;
import com.kkuil.kkuilapiinterface.anotation.InvokeCount;
import com.kkuil.kkuilapiinterface.utils.random.TextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小K
 * @Date 2023/7/18 16:00
 * @Description 生成随机文本
 */
@RestController
@Slf4j
public class RandomTextController {

    /**
     * @param minLen 最小长度
     * @param maxLen 最大长度
     * @return 随机文本
     * @Description 生成随机文本
     */
    @GetMapping("text/random")
    @Operation(summary = "生成随机文本")
    @Parameters({@Parameter(name = "minLen", description = "最小长度", required = true, in = ParameterIn.QUERY), @Parameter(name = "maxLen", description = "最大长度", required = true, in = ParameterIn.QUERY)})
    @InvokeCount(id = 2)
    public ResultUtil<String> getRandomText(int minLen, int maxLen) {
        if (minLen > maxLen) {
            return ResultUtil.fail(HttpStatus.BAD_REQUEST, "最小长度不能大于最大长度");
        }
        return ResultUtil.success(TextUtil.getRandomText(minLen, maxLen));
    }
}
