package com.kkuil.kkuilapi.constant;

/**
 * @Author 小K
 * @Date 2023/7/14 15:30
 * @Description 管理员常量
 */
public class AdminConst {
    /**
     * 管理员在请求头中的key
     */
    public static final String ADMIN_TOKEN_KEY = "admin_token";

    /**
     * 管理员token的密钥
     */
    public static final String ADMIN_TOKEN_SECRET = "kkuil_admin_token_secret";

    /**
     * token过期时间
     */
    public static final long ADMIN_TOKEN_TTL = 1000 * 60 * 60 * 24 * 7;

}
