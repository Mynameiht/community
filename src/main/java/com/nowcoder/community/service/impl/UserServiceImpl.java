package com.nowcoder.community.service.impl;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService, CommunityConstant {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextpath;

    @Override
    public User selectById(int id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public User selectByName(String username){

        User user = userMapper.selectByName(username);
        return user;
    }

    @Override
    public User selectByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }

    @Override
    public int insertUser(User user) {
        int i = userMapper.insertUser(user);
        return i;
    }

    @Override
    public int updateStatus(int id, int status) {
        int i = userMapper.updateStatus(154, 0);
        return i;
    }

    @Override
    public Map<String, Object> register(User user) {
        Map<String,Object> map = new HashMap<>();
        //空值处理
        if(user == null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg", "用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }
        //验证用户名是否存在
        User u = userMapper.selectByName(user.getUsername());
        if(u != null){
            map.put("usernameMsg", "该账号已存在");
            return map;
        }
        //验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if(u != null){
            map.put("emailMsg", "该邮箱已存在");
            return map;
        }
        //验证通过注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.md5(user.getPassword()+user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
        //激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = domain + contextpath + "/activation/"+user.getId() +"/"+user.getActivationCode();
        context.setVariable("url",url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMessage(user.getEmail(),"激活账号", content);
        return map;
    }

    public int activation(String userId,String code){
        User user = userMapper.selectById(Integer.parseInt(userId));
        if(user.getStatus() == 1){
            return ACTIVATION_REPEAT;
        }else if(user.getActivationCode().equals(code)){
            userMapper.updateStatus(Integer.parseInt(userId),1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }
}
