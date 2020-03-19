package com.lysong.friday.controller;

import com.alibaba.fastjson.JSONArray;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dto.RoleDto;
import com.lysong.friday.model.SysPermission;
import com.lysong.friday.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: LySong
 * @Date: 2020/3/17 23:11
 */
@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/listAllPermission")
    @ResponseBody
    public Results<JSONArray> listAllPermission(){
        return permissionService.listAllPermission();
    }

    @GetMapping("/listAllPermissionByRoleId")
    @ResponseBody
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto){
        log.info(getClass().getName() + ": param =" + roleDto);
        return permissionService.listAllPermissionByRoleId(roleDto.getId().intValue());
    }
}
