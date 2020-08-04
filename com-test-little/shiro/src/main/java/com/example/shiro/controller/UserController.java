package com.example.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/4 13:42
 */
@Controller
public class UserController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){

        model.addAttribute("msg","HelloShiro !");

        return "index";
    }

    @RequestMapping({"/user/add"})
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate(){
        return "user/update";
    }

}
