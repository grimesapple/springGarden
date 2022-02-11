package com.djq.springGarden.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * @author duanjiaqi
 * @date 2022/2/4
 */
public class JwtUtil {
    /**
     * token有效期为1小时
     */
    private static long time = 1000 * 60 * 60;
    private static String signature = "admin";

    public static String createToken() {
        JwtBuilder builder = Jwts.builder();
        return builder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","admin")
                .claim("password","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
    }
}
