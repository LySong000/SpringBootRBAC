package com.lysong.friday.service;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dto.UserDto;
import com.lysong.friday.model.SysUser;

public interface UserService {
	SysUser getUser(String username);

    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);

    Results save(SysUser sysUser, Integer roleId);

    SysUser getUserByPhone(String telephone);

    SysUser getUserById(Long id);

    Results<SysUser> updateUser(UserDto userDto, Integer roleId);

    int deleteByUserId(Long id);

    Results<SysUser> getUserByFuzzyUserName(String username, Integer offset, Integer limit);

    SysUser getUserByEmail(String email);
}
