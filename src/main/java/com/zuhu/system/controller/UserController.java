package com.zuhu.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuhu.system.pojo.User;
import com.zuhu.system.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController extends AbstractController {
    @Resource
    private UserService userService;
    
    @RequestMapping("/add")
    @ResponseBody
    private Object addUser(@ModelAttribute User user){
        return generatorResult(userService.insert(user));
    }
    
    @RequestMapping("/list")
    @ResponseBody
    private Object list(@ModelAttribute User user){
        return generatorResult(userService.insert(user));
    }
    
    @RequestMapping("/login")
    @ResponseBody
    private Object login(@ModelAttribute User user){
        return generatorResult(userService.validateLogin(user.getCode(), user.getPassword()));
    }
    
    @RequestMapping("/update")
    @ResponseBody
    private Object update(@ModelAttribute User user){
        return generatorResult(userService.update(user));
    } 
    
    @RequestMapping("/logout")
    @ResponseBody
    private Object logout(@ModelAttribute User user){
        return generatorResult(userService.validateLogin(user.getCode(), user.getPassword()));
    }
}
