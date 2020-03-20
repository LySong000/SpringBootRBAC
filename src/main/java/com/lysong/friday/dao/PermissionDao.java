package com.lysong.friday.dao;

import com.lysong.friday.base.result.Results;
import com.lysong.friday.model.SysPermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
    @Select("select * from sys_permission t")
    List<SysPermission> findAll();

    @Select("select p.* from sys_permission p inner join sys_role_permission pr on p.id = pr.permissionId where pr.roleId = #{roleId} order by p.sort")
    List<SysPermission> findPermissionByRoleId(int roleId);

    @Select("select * from sys_permission")
    List<SysPermission> getAllMenu();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_permission(parentId, name, css, href, type, permission, sort) values(#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
    int save(SysPermission sysPermission);

    @Select("select * from sys_permission where id = #{id}")
    SysPermission getPermissionById(Integer id);


    int update(SysPermission sysPermission);

    @Delete("delete t from sys_permission t where t.id = #{id}")
    int deleteById(Integer id);

    @Delete("delete t from sys_permission t where t.parentId = #{parentId}")
    int deleteByParentId(Integer parentId);

    @Select("select sp.* from sys_role_user sru "+
            "inner join sys_role_permission srp on srp.roleId = sru.roleId "+
            "left join sys_permission sp on srp.permissionId = sp.id"+
            " where sru.userId = #{userId}")
    List<SysPermission> listByUserId(@Param("userId") Long userId);
}
