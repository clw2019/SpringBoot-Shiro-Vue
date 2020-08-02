package com.clw.common.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: clw
 * @Description: Shiro 原生的 Token 中存在用户名和密码以及其他信息 [验证码，记住我].
 *               在 JWT 的 Token 中因为已将用户名和密码通过加密处理整合到一个加密串中，所以只需要一个 token 字段即可
 * @Date: 2020/7/26 15:30
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
