package com.nowcoder.community.controller;


import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.pojo.Page;
import com.nowcoder.community.pojo.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model,Page page){
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");
        System.out.println(page);
        List<DiscussPost> discussPosts = discussPostService.selectDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> list = new ArrayList<>();

        if (discussPosts != null) {
            for (DiscussPost discussPost : discussPosts) {
                Map<String,Object> map = new HashMap<>();
                User user = userService.selectById(Integer.parseInt(discussPost.getUserId()));
                map.put("user", user);
                map.put("discussPost", discussPost);
                list.add(map);
            }
        }
        model.addAttribute("list", list);
        System.out.println(list);
        return "/index";
    }
}
