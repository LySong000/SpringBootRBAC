package com.lysong.friday.service.impl;

import com.lysong.friday.base.result.Results;
import com.lysong.friday.dao.RoleUserDao;
import com.lysong.friday.model.SysRole;
import com.lysong.friday.model.SysRoleUser;
import com.lysong.friday.service.RoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: LySong
 * @Date: 2020/3/16 23:50
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Resource
    private RoleUserDao roleUserDao;

    @Override
    public Results getSysRoleUserByUserId(Integer userId) {
        //根据id查找用户
        SysRoleUser sysRoleUser = roleUserDao.getSysRoleUserByUserId(userId);
        if(sysRoleUser != null){
            return Results.success(sysRoleUser);
        }else {
            return Results.success();
        }
    }

    @Override
    @Transactional
    public int deleteByUserId(Integer userId) {
        //根据id删除角色和用户的关联
        return roleUserDao.deleteByUserId(userId);
    }
}
