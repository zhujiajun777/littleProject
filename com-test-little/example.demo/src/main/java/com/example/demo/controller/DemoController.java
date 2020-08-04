package com.example.demo.controller;

import com.example.demo.model.DemoUser;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/7/10 15:46
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/com/example/demo")
    @ResponseBody
    public DemoUser demo(){
        return demoService.selectOneDemoUser();
    }

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
