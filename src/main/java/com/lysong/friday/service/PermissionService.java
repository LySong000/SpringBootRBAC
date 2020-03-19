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
}
