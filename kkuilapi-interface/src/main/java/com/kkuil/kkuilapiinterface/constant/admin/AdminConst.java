package com.kkuil.kkuilapiinterface.constant.admin;

/**
 * @Author 小K
 * @Date 2023/7/14 15:30
 * @Description 管理员常量
 */
public class AdminConst {
    /**
     * 管理员在请求头中的key
     */
    public static final String ADMIN_TOKEN_KEY = "kkuil_admin_token_key";

    /**
     * 永久token
     */
    public static final String ADMIN_PERMANENT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJhY2NvdW50IjoiS2t1aWwifQ._2y3TU55LUkc3GJYuUtXSZQoPbmxFihiPl8o_AZNBf0";

    /**
     * 管理员token的密钥
     */
    public static final String ADMIN_TOKEN_SECRET = "kkuil_admin_token_secret";

    /**
     * token过期时间
     */
    public static final long ADMIN_TOKEN_TTL = 1000 * 60 * 60 * 24 * 7;

    /**
     * 管理员密码盐
     */
    public static final String ADMIN_PWD_SALT = "kkuil_admin_pwd_salt";

}
