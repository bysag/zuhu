package com.zuhu.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zuhu.system.pojo.Role;
import com.zuhu.system.service.FuncService;
import com.zuhu.system.service.RoleService;

@Controller
@RequestMapping("/role/")
public class RoleController extends AbstractController {

    @Resource
    private RoleService roleService;
    
    private FuncService funcService;

    @RequestMapping("/add")
    public Object add(@ModelAttribute Role role) {
        return generatorResult(roleService.insert(role));
    }

    @RequestMapping("/gets")
    public Object gets(
            @RequestParam(value = "limit", required = true, defaultValue = "10") int limit,
            @RequestParam(value = "offset", required = true, defaultValue = "0") int offset) {
        return generatorResult(roleService.getRoles(limit, offset));
    }
    
    @RequestMapping("/get")
    public Object get( @RequestParam(value = "roleId", required = true, defaultValue = "-1l") long roleId){
        return generatorResult(roleService.getRole(roleId));
    }
    
    @RequestMapping("/update")
    public Object update(@ModelAttribute Role role){
        return generatorResult(roleService.update(role));
    }
    
    @RequestMapping("/getRoleFuncs")
    public Object getRoleFuncs(@RequestParam(value="roleIds" ,required=true) Long [] roleIds){
        return generatorResult(funcService.getFuncsByRoleIds(roleIds));
    }
    
}
