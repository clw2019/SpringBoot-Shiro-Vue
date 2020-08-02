package com.clw.sys.service;

import com.clw.sys.domain.Menu;
import com.clw.sys.domain.Role;
import com.clw.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author clw
 * @since 2020-07-21
 */
public interface UserService extends IService<User> {

    List<Role> queryRolesById(Long id);

    List<Menu> queryPermissionById(Long id);
}
