package com.lysong.friday.service;

import com.lysong.friday.base.result.Results;
import com.lysong.friday.model.SysPermission;

/**
 * @Author: LySong
 * @Date: 2020/3/17 23:13
 */
public interface PermissionService {

    Results listAllPermission();

    Results<SysPermission> listAllPermissionByRoleId(int intValue);

    Results<SysPermission> getAllMenu();

    Results<SysPermission> save(SysPermission sysPermission);

    SysPermission getPermissionById(Integer id);

    Results<SysPermission> update(SysPermission sysPermission);

    Results<SysPermission> deleteById(Integer id);

    Results getMenu(Long userId);
}
