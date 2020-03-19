package com.lysong.friday.service;

import com.lysong.friday.base.result.Results;
import com.lysong.friday.dto.RoleDto;
import com.lysong.friday.model.SysRole;

/**
 * @Author: LySong
 * @Date: 2020/3/16 20:07
 */
public interface RoleService {

    Results<SysRole> getAllRoles();

    Results<SysRole> getAllRoleByPages(Integer offset, Integer limit);

    Results<SysRole> getRoleByFuzzyRoleName(String rolename,Integer offset, Integer limit);

    Results<SysRole> save(RoleDto roleDto);

    SysRole getRoleById(Integer id);

    int update(RoleDto roleDto);

    int deleteById(Integer id);
}
