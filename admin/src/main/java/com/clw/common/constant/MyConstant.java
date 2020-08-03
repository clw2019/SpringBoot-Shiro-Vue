package com.clw.common.constant;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/7/21 22:47
 */
public interface MyConstant {
    // 调用成功返回值
    Integer SUCCESS_CODE = 200;

    String USERNAME_KEY = "username";
    // 用户登录传入的密码加密后
    String PASSWORD_ENCRYPTION_KEY = "password";

    /***
     * JWT相关常量
     */
    // token 过期时间
    Long JWT_EXPIRE_TIME = Long.valueOf(7*24*60*60*1000);

    // jwt 秘钥
    String JWT_SECRET = "secret";

    // Token的key
    String TOKEN_KEY = "Authorization";
}
