package com.newcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/hi")
    @ResponseBody
    public String hi(){
        return "hi";
    }
}
