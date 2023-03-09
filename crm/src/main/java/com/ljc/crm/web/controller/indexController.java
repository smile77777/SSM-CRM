package com.ljc.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    //可以省去，必须省去
    //@RequestMapping("http://127.0.0.1:8080/crm/")
    @RequestMapping("/")
    public String index(){
        //省去之后，因为有视图解析器
        //return "/WEB-INF/pages/index.jsp";
        return "index";
    }
}
