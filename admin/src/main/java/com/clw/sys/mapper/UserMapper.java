package com.clw.sys.mapper;

import com.clw.sys.domain.Menu;
import com.clw.sys.domain.Role;
import com.clw.sys.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author clw
 * @since 2020-07-21
 */
public interface UserMapper extends BaseMapper<User> {

    List<Role> queryRolesById(@Param("id") Long id);

    List<Menu> queryPermissionById(@Param("id") Long id);
}
