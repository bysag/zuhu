package com.zuhu.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zuhu.system.pojo.Func;
import com.zuhu.system.service.FuncService;

@Controller
@RequestMapping("/func/")
public class FuncController extends AbstractController {

    @Resource
    private FuncService funcService;

    @RequestMapping("/add/")
    public Object add(@ModelAttribute Func func) {
        return generatorResult(funcService.insert(func));
    }

    @RequestMapping("/update")
    public Object update(@ModelAttribute Func func) {
        return generatorResult(funcService.update(func));
    }

    @RequestMapping("/gets")
    public Object gets(
            @RequestParam(value = "limit", required = true, defaultValue = "10") int limit,
            @RequestParam(value = "offset", required = true, defaultValue = "0") int offset) {
        return generatorResult(funcService.getFuncs(limit, offset));
    }

    @RequestMapping("/get")
    public Object get(
            @RequestParam(value = "funcId", required = true, defaultValue = "-1l") long funcId) {
        return generatorResult(funcService.getFunc(funcId));
    }
}
