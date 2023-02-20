package com.newcoder.community.service;

import com.newcoder.community.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserById(int id);
}
