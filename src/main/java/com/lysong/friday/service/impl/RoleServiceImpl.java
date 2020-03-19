package com.lysong.friday.service.impl;

import com.lysong.friday.base.result.Results;
import com.lysong.friday.dao.PermissionDao;
import com.lysong.friday.dao.RoleDao;
import com.lysong.friday.dao.RolePermissionDao;
import com.lysong.friday.dto.RoleDto;
import com.lysong.friday.model.RolePermission;
import com.lysong.friday.model.SysRole;
import com.lysong.friday.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: LySong
 * @Date: 2020/3/16 20:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public Results<SysRole> getAllRoles() {
        //返回角色列表
        return Results.success(50,roleDao.getAllRoles());
    }

    @Override
    public Results<SysRole> getAllRoleByPages(Integer offset, Integer limit) {
        return Results.success(roleDao.countAllRoles().intValue(),roleDao.getAllRoleByPages(offset,limit));
    }

    @Override
    public Results<SysRole> getRoleByFuzzyRoleName(String roleName,Integer offset, Integer limit) {
        return Results.success(roleDao.countByFuzzyName(roleName).intValue(),roleDao.getRoleByFuzzyRoleName(roleName,offset,limit));
    }

    @Override
    @Transactional
    public Results<SysRole> save(RoleDto roleDto) {
        //先保存角色
        roleDao.saveRole(roleDto);
        int roleId = roleDao.getRoleIdByRoleName(roleDto.getName());
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        //保存角色对应的权限
        if(!CollectionUtils.isEmpty(permissionIds)){
            rolePermissionDao.save(Integer.valueOf(roleId),permissionIds);
        }
        return Results.success();
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public int update(RoleDto roleDto) {
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0);
        rolePermissionDao.deleteByRoleId(roleDto.getId());
        if(!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), roleDto.getPermissionIds());
        }
        int flag = roleDao.update(roleDto);
        if(flag != 0) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
//        int flag1 = rolePermissionDao.deleteByRoleId(id);
        int flag2 = roleDao.deleteById(id);
        if(flag2 != 0){
            return 1;
        }else {
            return 0;
        }
    }
}
