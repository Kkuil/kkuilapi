package com.kkuil.kkuilapi.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;

import static com.kkuil.kkuilapi.constant.AdminConst.ADMIN_TOKEN_TTL;

/**
 * @Author 小K
 * @Description token 工具包
 */
@Slf4j
public class TokenUtil {


    /**
     * 生成token
     *
     * @param data HashMap<String, Object>
     * @return String
     */
    public static String create(HashMap<String, Object> data, String secret) {
        try {
            return Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .setClaims(data)
                    .setExpiration(new Date(System.currentTimeMillis() + ADMIN_TOKEN_TTL))
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析token
     *
     * @param token String
     * @return String
     */
    public static Claims parse(String token, String secret) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}