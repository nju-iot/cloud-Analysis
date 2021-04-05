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
    public String sayHello(){
        return "home";
    }

}
