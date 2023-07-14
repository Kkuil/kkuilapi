-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS `db_kkuil_api` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 2. 使用数据库
USE `db_kkuil_api`;

-- 3. 创建表
-- 3.1 创建接口信息表
CREATE TABLE IF NOT EXISTS `tb_api_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `api_name` varchar(255) NOT NULL COMMENT '接口名称',
    `api_desc` varchar(255) NOT NULL COMMENT '接口描述',
    `api_url` varchar(255) NOT NULL COMMENT '接口地址',
    `api_type` varchar(255) NOT NULL COMMENT '接口类型',
    `api_status` varchar(255) NOT NULL COMMENT '接口状态',
    `api_create_time` datetime NOT NULL COMMENT '接口创建时间',
    `api_modify_time` datetime NOT NULL COMMENT '接口更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口信息表'