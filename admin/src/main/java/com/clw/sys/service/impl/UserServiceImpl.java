package com.clw.sys.service.impl;

import com.clw.sys.domain.Menu;
import com.clw.sys.domain.Role;
import com.clw.sys.domain.User;
import com.clw.sys.mapper.UserMapper;
import com.clw.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author clw
 * @since 2020-07-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public List<Role> queryRolesById(Long id) {
        List<Role> roleList = baseMapper.queryRolesById(id);
        return roleList;
    }

    @Override
    public List<Menu> queryPermissionById(Long id) {
        List<Menu> menuList = baseMapper.queryPermissionById(id);
        return menuList;
    }
}
