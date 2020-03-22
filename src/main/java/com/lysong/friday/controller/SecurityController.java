package com.lysong.friday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: LySong
 * @Date: 2020/3/20 22:06
 */
@Controller
public class SecurityController {

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/403.html")
    public String noPermission(){
        return "403";
    }
}
