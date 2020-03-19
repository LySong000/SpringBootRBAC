package com.lysong.friday.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    void save(Integer roleId, List<Long> permissionIds);

    @Delete("delete t from sys_role_permission t where t.roleId = #{roleId}")
    int deleteByRoleId(Integer roleId);
}
