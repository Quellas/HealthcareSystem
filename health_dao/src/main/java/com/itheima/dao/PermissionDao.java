package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.Set;

/**
 * 持久层Dao接口
 */
public interface PermissionDao {
    Set<Permission> findPermissionByRoleId(Integer roleId);
}
