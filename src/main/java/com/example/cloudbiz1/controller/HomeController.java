package com.example.cloudbiz1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xmx
 * @date 2021/3/25
 **/

@Controller
public class HomeController {

    @RequestMapping()
    public String sayHello() {
        return "home";
    }

    @RequestMapping("/main")
    public String jumpMain() {
        System.out.println("main!!!");
        return "main";
    }

    @RequestMapping("/china")
    public String jumpChina(){
        return "china";
    }

    @RequestMapping("/layui")
    public String layuiJump(){
        return "TestUI";
    }

}
