package com.example.netdisk.utils;

import com.example.netdisk.bo.JwtPayload;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JWTUtils {

    private static String secret = "123456567";

    //生成Token
    public static String CreateToken(JwtPayload payload) {
        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", payload.getUserName())
                .claim("Id", payload.getId())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //过期时间当前时间加24小时
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, secret)
                //拼接
                .compact();

        return jwtToken;
    }

    //验证token并返回Claims
    public static Claims verifyJwt(String token) {
        String[] tokens = token.split(" ");
        try {
            Claims result = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(tokens[1])
                    .getBody();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}
