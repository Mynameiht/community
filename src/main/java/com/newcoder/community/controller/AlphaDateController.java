package com.newcoder.community.controller;


import com.newcoder.community.service.AlphaService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaDateController {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/date")
    @ResponseBody
    public String date(){
        String date = alphaService.alphaDate();
        return date;
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request , HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String header = request.getHeader(name);
            System.out.println(name +":"+ header);
        }

        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter()){
            writer.write("<h1>你好<h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(path = "student" , method = RequestMethod.POST)
    @ResponseBody
    public String student(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "保存成功";
    }

    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView teacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",23);
        mav.setViewName("/demo/view");
        return mav;
    }

    //返回类型为String
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北大");
        model.addAttribute("age",120);
        return "/demo/view";
    }

    //返回json数据

    @RequestMapping(path = "emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> testJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",13);
        map.put("sex","男");
        return map;
    }

    @RequestMapping(path = "emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> emps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",13);
        map.put("sex","男");
        list.add(map);
        map = new HashMap<>();
        map.put("name","李四");
        map.put("age",14);
        map.put("sex","男");
        list.add(map);
        map = new HashMap<>();
        map.put("name","王五");
        map.put("age",17);
        map.put("sex","男");
        list.add(map);

        return list;
    }
}

