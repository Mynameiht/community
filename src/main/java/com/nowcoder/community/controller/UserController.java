package com.nowcoder.community.controller;


import com.nowcoder.community.pojo.User;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/selectById",method = RequestMethod.GET)
    @ResponseBody
    public User selectById(@RequestParam(name = "id") int id){

        User select = userService.selectById(id);
        return select;
    }


    @RequestMapping(path = "/selectByName",method = RequestMethod.GET)
    @ResponseBody
    public User selectByName(@RequestParam(name = "username") String username){

        User user = userService.selectByName(username);
        return user;
    }

    @RequestMapping(path = "/insertUser",method = RequestMethod.PUT)
    @ResponseBody
    public String insertUser(@RequestBody User user){
        try {
            System.out.println(1);
            userService.insertUser(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
