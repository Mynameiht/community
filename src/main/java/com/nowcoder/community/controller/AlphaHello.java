package com.nowcoder.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaHello {

    @RequestMapping("/hello")
    public String hello(){

        return "Hello Spring Boot";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getContextPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" +value);
        }

        try (
            PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>hahaha</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/student/{id}")
    @ResponseBody
    public String stu(
            @PathVariable(name = "id") int id){
        System.out.println(id);
        return "success";
    }


    @RequestMapping(value = "/student",method = RequestMethod.GET)
    @ResponseBody
    public String stu2(
            @RequestParam(name = "name",required = false,defaultValue = "zhangsan") String name,
            @RequestParam(name = "age",required = false,defaultValue = "18") int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public ModelAndView school(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","kunmingligong");
        mav.addObject("age","18");
        mav.setViewName("/demo/view");
        return mav;
    }


    @RequestMapping(path = "/getSchool",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "yunnandaxue");
        model.addAttribute("age", "100");
        return "/demo/view";
    }


    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();

        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);

        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();

        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);

        emp.put("name","李四");
        emp.put("age",24);
        emp.put("salary",9000.00);
        list.add(emp);

        emp.put("name","王五");
        emp.put("age",25);
        emp.put("salary",10000.00);
        list.add(emp);
        return list;
    }
}
