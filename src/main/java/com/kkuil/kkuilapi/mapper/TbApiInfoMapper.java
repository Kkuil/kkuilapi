package com.kkuil.kkuilapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkuil.kkuilapicommon.model.vo.interfaceInfo.InterfaceInfoListResDataWithAdminVO;
import com.kkuil.kkuilapicommon.model.vo.interfaceInfo.InterfaceInfoListResDataWithUserVO;
import org.apache.ibatis.annotations.Mapper;
import com.kkuil.kkuilapicommon.model.po.TbApiInfo;

import java.util.List;

/**
 * @Author 小K
 * @Description 针对表【tb_api_info(接口信息表)】的数据库操作Mapper
 * @Date 2023-07-14 12:35:43
 * @Entity com.kkuil.kkuilapicommon.model.po.TbApiInfo
 */
@Mapper
public interface TbApiInfoMapper extends BaseMapper<TbApiInfo> {

    /**
     * @param skip     跳过的条数
     * @param pageSize 每页条数
     * @param apiName  接口名称
     * @return 接口列表信息
     * @Description 管理员获取接口列表信息
     */
    List<InterfaceInfoListResDataWithAdminVO> listInterfaceInfoWithAdminLimit(
            int skip,
            int pageSize,
            String apiName
    );


    /**
     * @param apiName 接口名称
     * @return 接口列表信息总条数
     * @Description 管理员获取接口列表信息总条数
     */
    Integer listInterfaceInfoWithAdminNotLimit(
            String apiName
    );

    /**
     * @param skip     跳过的条数
     * @param pageSize 每页条数
     * @param apiName  接口名称
     * @return 接口列表信息
     * @Description 用户获取接口列表信息
     */
    List<InterfaceInfoListResDataWithUserVO> listInterfaceInfoWithUserLimit(int skip, Integer pageSize, String apiName);

    /**
     * @param apiName 接口名称
     * @return 接口列表信息总条数
     * @Description 用户获取接口列表信息总条数
     */
    int listInterfaceInfoWithUserNotLimit(String apiName);
}




