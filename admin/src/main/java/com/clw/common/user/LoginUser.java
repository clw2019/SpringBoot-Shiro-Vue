package com.clw.common.user;

import com.clw.sys.domain.Menu;
import com.clw.sys.domain.Role;
import com.clw.sys.domain.User;

import java.util.List;
import java.util.Set;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/8/1 21:13
 */
public class LoginUser {

    /**
     * 当前用户对象
     */
    private User user;
    /**
     * 当前用户具有的角色
     */
    private List<Role> roles;
    /**
     * 当前用户具有的url
     */
    private Set<String> urls;

    /**
     * 包括url+permission
     */
    private List<Menu> menus;
    /**
     * 当前用户具有的权限API:例如[user:add],[user:delete]...
     */
    private Set<String> permissions;

    /**
     * session id
     */
    private String id;
    /**
     * 用户 id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户主机地址
     */
    private String host;
    /**
     * 用户登录时系统 IP
     */
    private String systemHost;
    /**
     * 状态
     */
    private String status;
    /**
     * session 创建时间
     */
    private String startTimestamp;
    /**
     * session 最后访问时间
     */
    private String lastAccessTime;
    /**
     * 超时时间
     */
    private Long timeout;
    /**
     * 所在地
     */
    private String location;
    /**
     * 是否为当前登录用户
     */
    private Boolean current;
}
