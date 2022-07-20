package com.nowcoder.community.service.impl;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Override
    public List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit) {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(userId, offset, limit);
        return discussPosts;
    }

    @Override
    public int selectDiscussPostRows(int userId) {
        int i = discussPostMapper.selectDiscussPostRows(userId);
        return i;
    }
}
