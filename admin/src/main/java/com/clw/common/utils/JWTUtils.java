package com.clw.common.utils;

import com.clw.common.constant.MyConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @Author: clw
 * @Description: JWT工具类
 * @Date: 2020/7/26 16:14
 */
public class JWTUtils {

    /**
     * 根据自定义的负载生成token
     */
    public static final String generateToken(Map<String, Object> map) {
        return Jwts.builder()
                .setClaims(map) // 自定义内容
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + MyConstant.JWT_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, MyConstant.JWT_SECRET)
                .compact();
    }

    /**
     * 根据token获取负载
     */
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(MyConstant.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 根据负载获取用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String username = (String) claims.get(MyConstant.USERNAME_KEY);
        return username;
    }

    /**
     * 判断token是否过期
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }

    /**
     * 判断密码是否正确
     */
    public static boolean verify(String token, String password) {
        Claims claims = getClaimsFromToken(token);
        String encryptionPassword = (String) claims.get(MyConstant.PASSWORD_ENCRYPTION_KEY);
        if (encryptionPassword.equals(password)) {
            return true;
        }
        return false;
    }
}
