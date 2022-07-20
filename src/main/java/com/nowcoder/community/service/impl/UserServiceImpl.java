package com.nowcoder.community.service.impl;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.User;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
}
