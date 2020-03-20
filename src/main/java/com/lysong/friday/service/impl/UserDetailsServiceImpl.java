package com.lysong.friday.service.impl;

import com.lysong.friday.dao.PermissionDao;
import com.lysong.friday.dto.LoginUser;
import com.lysong.friday.model.SysPermission;
import com.lysong.friday.model.SysUser;
import com.lysong.friday.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: LySong
 * @Date: 2020/3/20 21:35
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUser(s);
        if(sysUser == null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }else if(sysUser.getStatus() == SysUser.Status.DISABLED){
            throw new LockedException("用户被锁定,请联系管理员");
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser,loginUser);
        List<SysPermission> permissions = permissionDao.listByUserId(sysUser.getId());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
