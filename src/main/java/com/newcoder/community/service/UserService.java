package com.newcoder.community.service;

import com.newcoder.community.entity.LoginTicket;
import com.newcoder.community.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    User findUserById(int id);
    Map<String, Object> register(User user);
    int activation(String userId,String code);
    Map<String,Object> login(String username,String password ,int expiredSeconds);
    LoginTicket getLoginTicket(String ticket);
    void logout(String ticket);
    int updateHeader(int userId,String headerUrl);
    int updatePassword(int userId,String password);
}
