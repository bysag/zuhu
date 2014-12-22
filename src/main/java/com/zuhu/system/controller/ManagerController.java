package com.zuhu.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuhu.system.pojo.Manager;
import com.zuhu.system.service.ManagerService;
import com.zuhu.system.service.RoleService;

@Controller
@RequestMapping("/manager/")
public class ManagerController extends AbstractController {

    @Resource
    private ManagerService managerService;

    @Resource
    private RoleService roleService;

    @RequestMapping("/add")
    @ResponseBody
    public Object addManager(@ModelAttribute Manager manager) {
        return generatorResult(managerService.addManager(manager));
    }

    @RequestMapping("/gets")
    @ResponseBody
    public Object getManagers(
            @RequestParam(value = "limit", required = true, defaultValue = "10") int limit,
            @RequestParam(value = "offset", required = true, defaultValue = "0") int offset) {
        return generatorResult(managerService.getManagers(limit, offset));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object getManager(
            @RequestParam(value = "code", required = true, defaultValue = "") String managerCode) {
        return generatorResult(managerService.getManager(managerCode));
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object updateManager() {
        return "";
    }

    @RequestMapping("/getprivileges")
    @ResponseBody
    public Object getManagerPrivileges(
            @RequestParam(value = "code", required = true, defaultValue = "") String managerCode) {
        return generatorResult(roleService.getRolesByManageId(managerCode));
    }

}
