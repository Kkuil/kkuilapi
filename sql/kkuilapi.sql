-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS `db_kkuil_api` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 2. 使用数据库
USE `db_kkuil_api`;

-- 3. 创建表
-- 3.1 接口信息表
CREATE TABLE IF NOT EXISTS `tb_api_info`
(
    `id`              int(11)                                                        NOT NULL AUTO_INCREMENT COMMENT '接口ID',
    `api_name`        varchar(255)                                                   NOT NULL COMMENT '接口名称',
    `api_desc`        varchar(255) COMMENT '接口描述',
    `api_url`         varchar(255)                                                   NOT NULL COMMENT '接口地址',
    `api_method`      varchar(255)                                                   NOT NULL COMMENT '请求方法',
    `api_param`       varchar(255) COMMENT '请求参数',
    `api_status`      varchar(255)                                                   NOT NULL COMMENT '接口状态',
    `api_req_example` varchar(255)                                                   NOT NULL COMMENT '请求示例',
    `api_res_example` varchar(255)                                                   NOT NULL COMMENT '响应示例',
    `api_count`      int(11) unsigned DEFAULT 0 COMMENT '接口调用次数',
    `api_create_time` DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '接口创建时间',
    `api_modify_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '接口更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='接口信息表';

-- 3.1 管理员信息表
CREATE TABLE IF NOT EXISTS `tb_admin_info`
(
    `id`       int unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
    `account`  char(7)      NOT NULL COMMENT '管理员账号',
    `password` varchar(255) COMMENT '管理员密码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='管理员信息表';