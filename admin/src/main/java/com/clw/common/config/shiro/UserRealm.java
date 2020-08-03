package com.clw.common.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clw.common.user.LoginUser;
import com.clw.common.utils.JWTUtils;
import com.clw.sys.domain.Menu;
import com.clw.sys.domain.Role;
import com.clw.sys.domain.User;
import com.clw.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/7/26 15:18
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 必须重写此方法，不然shiro报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 角色和权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // 如果为超级管理员，则赋予所有权限
        if (loginUser.getUser().getType() == 0) {
            authorizationInfo.addStringPermission("*:*");
        } else {
            // 从指定集合复制元素，且与所复制元素相同的初始容量
            List<String> permissionList = new ArrayList<>(loginUser.getPermissions());
            List<Role> roleList = loginUser.getRoles();
            // 授权角色
            if (!CollectionUtils.isEmpty(roleList)) {
                for (Role role : roleList) {
                    authorizationInfo.addRole(role.getRoleName());
                }
            }
            // 授权权限
            if (!CollectionUtils.isEmpty(permissionList)) {
                for (String permission : permissionList) {
                    if (permission != null && !"".equals(permission)) {
                        authorizationInfo.addStringPermission(permission);
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        String username = JWTUtils.getUsernameFromToken(token);
        if (username == null) {
            throw new AuthenticationException("token错误，请重新登入！");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            throw new AccountException("用户名不存在！");
        }
        if (JWTUtils.isTokenExpired(token)) {
            throw new AuthenticationException("token过期！");
        }
        if (!JWTUtils.verify(token, user.getPassword())) {
            throw new CredentialsException("密码错误！");
        }
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定！");
        }
        // 获取用户的角色
        List<Role> roleSet = userService.queryRolesById(user.getId());
        // 获取用户的权限
        List<Menu> menuList = userService.queryPermissionById(user.getId());
        HashSet<String> urls = new HashSet<>();
        HashSet<String> perms = new HashSet<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Menu menu : menuList) {
                String url = menu.getUrl();
                String per = menu.getPerms();
                if (menu.getType() == 0 && !StringUtils.isEmpty(url)) {
                    urls.add(url);
                }
                if (menu.getType() == 1 && !StringUtils.isEmpty(per)) {
                    perms.add(menu.getPerms());
                }
            }
        }
        // 过滤出url和用户的权限
        LoginUser loginUser = new LoginUser();
        loginUser.setRoles(roleSet);
        loginUser.setUser(user);
        loginUser.setMenus(menuList);
        loginUser.setUrls(urls);
        loginUser.setPermissions(perms);
        return new SimpleAuthenticationInfo(loginUser, token, getName());
    }
}
