package com.lysong.friday.service;

import com.lysong.friday.base.result.Results;

/**
 * @Author: LySong
 * @Date: 2020/3/16 23:47
 */
public interface RoleUserService {
    Results getSysRoleUserByUserId(Integer userId);

    int deleteByUserId(Integer userId);
}
