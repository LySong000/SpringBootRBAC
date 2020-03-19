package com.lysong.friday.controller;

import com.lysong.friday.base.result.ResponseCode;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dto.UserDto;
import com.lysong.friday.model.SysUser;
import com.lysong.friday.service.RoleUserService;
import com.lysong.friday.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: LySong
 * @Date: 2020/3/16 23:43
 */
@RestController
@RequestMapping("roleuser")
@Slf4j
public class RoleUserController {

    @Resource
    RoleUserService roleUserService;

    @PostMapping("/getRoleUserByUserId")
    public Results getRoleUserByUserId(Integer userId) {
        log.info("getRoleUserByUserId param:" + userId);
        return roleUserService.getSysRoleUserByUserId(userId);
    }

}
