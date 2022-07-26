package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id ,int status);

    Map<String,Object> register(User user);

    int activation(String userId,String code);
}
