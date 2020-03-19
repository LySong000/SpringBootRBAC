package com.lysong.friday.service.impl;

import com.lysong.friday.base.result.Results;
import com.lysong.friday.dao.RoleDao;
import com.lysong.friday.dao.RoleUserDao;
import com.lysong.friday.dao.UserDao;
import com.lysong.friday.dto.UserDto;
import com.lysong.friday.model.SysRoleUser;
import com.lysong.friday.model.SysUser;
import com.lysong.friday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Resource
	private RoleUserDao roleUserDao;

	@Override
	public SysUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit) {
		//返回需要的count，user-list
		return Results.success(userDao.countAllUsers().intValue(),userDao.getAllUsersByPage(offset,limit));
	}

	@Override
	public Results save(SysUser user, Integer roleId) {
		if(roleId != null){
			//保存user对象
			userDao.save(user);
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setRoleId(roleId);
			sysRoleUser.setUserId(user.getId().intValue());
			roleUserDao.save(sysRoleUser);
			//存储roleUser
			return Results.success();
		}
		//保存做了两件事情
		return Results.failure();
	}

	@Override
	public SysUser getUserByPhone(String telephone) {
		return userDao.getUserByPhone(telephone);
	}

	@Override
	public SysUser getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public Results<SysUser> updateUser(UserDto userDto, Integer roleId) {
		if(roleId == null){
			return Results.failure();
		}else {
			//sysuser
			userDao.updateUser(userDto);
			//sysroleuser
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setUserId(userDto.getId().intValue());
			sysRoleUser.setRoleId(roleId);
			if(roleUserDao.getSysRoleUserByUserId(userDto.getId().intValue()) != null){
				roleUserDao.updateSysRoleUser(sysRoleUser);
			}else {
				roleUserDao.save(sysRoleUser);
			}
			return Results.success();
		}
	}

	@Override
	public int deleteByUserId(Long id) {
		roleUserDao.deleteByUserId(id.intValue());
		return userDao.deleteByUserId(id.intValue());
	}

	@Override
	public Results<SysUser> getUserByFuzzyUserName(String username, Integer offset, Integer limit) {
		//返回需要的count，user-list
		return Results.success(userDao.getUserByFuzzyUserName(username).intValue(),userDao.getUserByFuzzyUserNameByPage(username,offset,limit));
	}

}
