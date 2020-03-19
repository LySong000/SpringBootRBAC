package com.lysong.friday.controller;

import com.alibaba.fastjson.JSONArray;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dao.PermissionDao;
import com.lysong.friday.dto.RoleDto;
import com.lysong.friday.model.SysPermission;
import com.lysong.friday.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/menuAll")
    @ResponseBody
    public Results getMenuAll(){
        return permissionService.getAllMenu();
    }

    @PostMapping("/add")
    @ResponseBody
    public Results<SysPermission> savePermission(@RequestBody SysPermission sysPermission){
        return permissionService.save(sysPermission);
    }

    @GetMapping("/edit")
    public String editPermission(Integer id, Model model){
        model.addAttribute("sysPermission", permissionService.getPermissionById(id));
        return "permission/permission-add";
    }

    @PostMapping("/edit")
    @ResponseBody
    public Results<SysPermission> updatePermission(@RequestBody SysPermission sysPermission){
        return permissionService.update(sysPermission);
    }

    @GetMapping("/add")
    public String addPermission(Model model){
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    @GetMapping("/delete")
    @ResponseBody
    public Results<SysPermission> deletePermisson(Integer id){
        return permissionService.deleteById(id);
    }
}
