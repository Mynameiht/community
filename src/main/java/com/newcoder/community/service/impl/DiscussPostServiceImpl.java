package com.newcoder.community.service.impl;

import com.newcoder.community.dao.DiscussPostMapper;
import com.newcoder.community.entity.DiscussPost;
import com.newcoder.community.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Override
    public List<DiscussPost> findDiscussPosts(String userId, int limit, int offset) {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(userId, limit, offset);
        return list;
    }

    @Override
    public int findDiscussPostRows(String userId) {
        int rows = discussPostMapper.selectDiscussPostRows(userId);
        return rows;
    }
}
