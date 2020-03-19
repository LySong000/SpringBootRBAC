package com.lysong.friday.dao;

import com.lysong.friday.model.SysPermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {
    @Select("select * from sys_permission t")
    List<SysPermission> findAll();

    @Select("select p.* from sys_permission p inner join sys_role_permission pr on p.id = pr.permissionId where pr.roleId = #{roleId} order by p.sort")
    List<SysPermission> findPermissionByRoleId(int roleId);
}
