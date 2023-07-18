package com.kkuil.kkuilapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkuil.kkuilapi.model.po.TbAdminInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 小K
 * @Description 针对表【tb_admin_info(管理员信息表)】的数据库操作Mapper
 * @Date 2023-07-14 16:16:03
 * @Entity com.kkuil.kkuilapi.model.po.TbAdminInfo
 */
@Mapper
public interface TbAdminInfoMapper extends BaseMapper<TbAdminInfo> {

}




