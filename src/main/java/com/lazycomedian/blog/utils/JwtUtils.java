package com.lazycomedian.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtils {

    // 有效期为 60 * 60 * 1000 一个小时
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    // 设置密钥明文
    public static final String JWT_KEY = "lazyComedian";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成JWT
     *
     * @param subject token中要存放的数据（JSON格式）
     */
    public static String createJWT(String subject) {
        final JwtBuilder builder = getJwtBuilder(subject, null, getUUID()); //设置过期时间
        return builder.compact();
    }

    /**
     * 生成JWT
     *
     * @param subject   token中要存放的数据（JSON格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String subject, Long ttlMillis) {
        final JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID()); //设置过期时间
        return builder.compact();
    }

    /**
     * 创建token
     *
     * @param id        唯一键
     * @param subject   token中要存放的数据（JSON格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        final JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id); //设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date((nowMillis));

        if (ttlMillis == null) ttlMillis = JwtUtils.JWT_TTL;

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) //唯一ID
                .setSubject(subject) //主题 可以是JSON数据
                .setIssuer(JWT_KEY) //签发者
                .setIssuedAt(now) //签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256堆成加密算法签名，第二个参数为密钥
                .setExpiration(expDate); //设置过期时间
    }


    /**
     * 生成加密后的密钥 secretKey
     *
     * @return secretKey
     */
    public static SecretKey generalKey() {
        final byte[] encodedKey = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析JWT
     *
     * @param jwt 需解析的jwt token字符串
     */
    public static Claims parseJWT(String jwt) throws Exception {
        final SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}
