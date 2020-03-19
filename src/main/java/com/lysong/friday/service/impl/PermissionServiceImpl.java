package com.lysong.friday.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dao.PermissionDao;
import com.lysong.friday.model.SysPermission;
import com.lysong.friday.service.PermissionService;
import com.lysong.friday.util.TreeUtils;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: LySong
 * @Date: 2020/3/17 23:15
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;
    @Override
    public Results listAllPermission() {
        log.info(getClass().getName() + ".listAllPermission()");
        List datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0,datas,array);
        return Results.success(array);
    }

    @Override
    public Results<SysPermission> listAllPermissionByRoleId(int roleId) {
        List<SysPermission> sysPermissions = permissionDao.findPermissionByRoleId(roleId);
        return Results.success(0,sysPermissions);
    }
}
