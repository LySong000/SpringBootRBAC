package com.lysong.friday.dto;

import com.lysong.friday.model.SysRole;

import java.util.List;

/**
 * @Author: LySong
 * @Date: 2020/3/17 22:48
 */
public class RoleDto extends SysRole {
    private static final long serialVersionUID = -5784234789156935003L;

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
