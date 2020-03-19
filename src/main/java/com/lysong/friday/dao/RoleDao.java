package com.lysong.friday.dao;

import com.lysong.friday.dto.RoleDto;
import com.lysong.friday.model.SysRole;
import com.lysong.friday.model.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select * from sys_role t")
    List<SysRole> getAllRoles();

    @Select("select count(*) from sys_role t")
    Long countAllRoles();

    @Select("select * from sys_role t order by t.id limit #{startPosition},#{limit}")
    List<SysRole> getAllRoleByPages(Integer startPosition, Integer limit);

    @Select("select count(*) from sys_role t where t.name like '%${roleName}%'")
    Long countByFuzzyName(String roleName);

    @Select("select id from sys_role t where t.name = #{roleName}")
    int getRoleIdByRoleName(String roleName);

    @Select("select * from sys_role t where t.name like '%${roleName}%' limit #{startPosition}, #{limit}")
    List<SysRole> getRoleByFuzzyRoleName(@Param("roleName")String roleName, @Param("startPosition")Integer startPosition, @Param("limit")Integer limit);

    int saveRole(SysRole role);

    @Select("select * from sys_role t where t.id = #{id}")
    SysRole getRoleById(Integer id);

    int update(SysRole sysRole);

    @Delete("delete t from sys_role t where t.id = #{id}")
    int deleteById(Integer id);
}
