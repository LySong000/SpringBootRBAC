package com.lysong.friday.controller;

import com.lysong.friday.base.result.PageTableRequest;
import com.lysong.friday.base.result.ResponseCode;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dao.RoleUserDao;
import com.lysong.friday.dto.UserDto;
import com.lysong.friday.model.SysRoleUser;
import com.lysong.friday.model.SysUser;
import com.lysong.friday.service.RoleUserService;
import com.lysong.friday.service.UserService;
import com.lysong.friday.util.MD5;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

	@Resource
	private UserService userService;

	@GetMapping("/{username}")
    @ResponseBody
	public SysUser user(@PathVariable String username) {
	    log.info("UserController.user(): param ( username = " + username +" )");
		return userService.getUser(username);
	}

	@GetMapping("/list")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:query')")
	@ApiOperation(value = "分页获取用户信息",notes = "分页获取用户信息")
	@ApiImplicitParam(name = "request",value = "分页查询实体类",required = false)
	public Results<SysUser> getUsers(PageTableRequest pageTableRequest) {
		//计算数据搜索起始点
		pageTableRequest.countOffset();
		//返回分页数据
		return userService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
	}

	@GetMapping("/add")
	@PreAuthorize("hasAuthority('sys:user:add123')")
	public String addUser(Model model) {
		//避免出现错误
		model.addAttribute(new SysUser());
		return "user/user-add";
	}

	@PostMapping("/add")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:add')")
	public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
		SysUser sysUser = null;
		sysUser = userService.getUserByPhone(userDto.getTelephone());
		//手机号避免重复
		if(sysUser != null && (sysUser.getTelephone().equals(userDto.getTelephone()))){
			return Results.failure(ResponseCode.PHONE_REPEAT.getCode(), ResponseCode.PHONE_REPEAT.getMessage());
		}
		//设置默认状态
		userDto.setStatus(1);
		//MD5加密
		userDto.setPassword(MD5.crypt(userDto.getPassword()));
		return userService.save(userDto,roleId);
	}

	@GetMapping("/edit")
	@PreAuthorize("hasAuthority('sys:user:add')")
	public String addUser(Model model,SysUser sysUser) {
		//根据id得到一个用户数据，渲染到前端
		model.addAttribute(userService.getUserById(sysUser.getId()));
		return "user/user-edit";
	}

	@PostMapping("/edit")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:add')")
	public Results<SysUser> updateUser(UserDto userDto, Integer roleId) {
		SysUser sysUser = null;
		//验证用户名是否重复
		sysUser = userService.getUser(userDto.getUsername());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
		}
		//得到表单数据，同样验证手机号
		sysUser = userService.getUserByPhone(userDto.getTelephone());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.PHONE_REPEAT.getCode(), ResponseCode.PHONE_REPEAT.getMessage());
		}
		sysUser = userService.getUserByEmail(userDto.getEmail());
		//验证邮箱是否重复
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
		}

		userDto.setStatus(1);
		userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		//保存数据
		return userService.updateUser(userDto,roleId);
	}

	@GetMapping("/delete")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:del')")
	public Results deleteUser(UserDto userDto) {
		//删除用户
		int count = userService.deleteByUserId(userDto.getId());
		if(count > 0){
			//返回成功消息
			return Results.success();
		}
		//返回失败消息
		return Results.failure();
	}

	@GetMapping("/findUserByFuzzyUserName")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:query')")
	public Results<SysUser> findUserByFuzzyUserName(PageTableRequest pageTableRequest, String username) {
		log.info("UserController.findUserByFuzzyUserName(): param ( username = " + username +" )");
		if(username == null){
			return userService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
		}
		//计算数据搜索起始点
		//模糊查询
		pageTableRequest.countOffset();
		return userService.getUserByFuzzyUserName(username, pageTableRequest.getOffset(),pageTableRequest.getLimit());
	}

	@PostMapping("/changePassword")
	@ApiOperation(value = "修改密码")
	@ResponseBody
	public Results<SysUser> changePassword(String username,String oldPassword,String newPassword){
		return userService.changePassword(username,oldPassword,newPassword);
	}

	/**
	 * 时间格式处理
	 */
	String pattern = "yyyy-MM-dd";
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request){
		binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(pattern),true));
	}

}
