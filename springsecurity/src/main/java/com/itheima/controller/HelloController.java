package com.itheima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    //表示用户必须拥有add权限才能调用当前方法
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('add')")
    public String add(){
        System.out.println("add.....");
        return null;
    }

    //表示用户必须拥有ROLE_ADMIN角色才能调用当前方法
    @RequestMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(){
        System.out.println("update.....");
        return null;
    }

    //表示用户必须拥有ABC角色才能调用当前方法
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ABC')")
    public String delete(){
        System.out.println("delete.....");
        return null;
    }








}
