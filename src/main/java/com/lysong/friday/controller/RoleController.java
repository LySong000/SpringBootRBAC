package com.lysong.friday.controller;

import com.lysong.friday.base.result.PageTableRequest;
import com.lysong.friday.base.result.Results;
import com.lysong.friday.dto.RoleDto;
import com.lysong.friday.model.SysRole;
import com.lysong.friday.model.SysUser;
import com.lysong.friday.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: LySong
 * @Date: 2020/3/16 20:05
 */
@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/all")
    @ResponseBody
    public Results<SysRole> getAll() {
        log.info("RoleController.getAll()");
        return roleService.getAllRoles();
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        model.addAttribute("sysRole",new SysRole());
        return "role/role-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Results saveRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @GetMapping("/delete")
    @ResponseBody
    public Results deleteRole(Integer id){
        int flag = roleService.deleteById(id);
        if(flag == 1) {
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    @GetMapping("/edit")
    public String editRole(Model model, SysRole sysRole) {
        model.addAttribute("sysRole",roleService.getRoleById(sysRole.getId()));
        return "role/role-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    public Results editRole(@RequestBody RoleDto roleDto) {
        int flag = roleService.update(roleDto);
        if(flag != 0){
            return Results.failure();
        }else {
            return Results.success();
        }
    }


    @GetMapping("/list")
    @ResponseBody
    public Results<SysRole> list(PageTableRequest request) {
        log.info("RoleController.list");
        request.countOffset();
        return roleService.getAllRoleByPages(request.getOffset(),request.getLimit());
    }

    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest request,String roleName) {
        log.info("RoleController.findRoleByFuzzyRoleName()");
        request.countOffset();
        if (roleName == null){
            return roleService.getAllRoles();
        }
        return roleService.getRoleByFuzzyRoleName(roleName,request.getOffset(),request.getLimit());
    }

}
