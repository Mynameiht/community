package com.nowcoder.community.service;

import com.nowcoder.community.pojo.DiscussPost;

import java.util.List;

public interface DiscussPostService {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    int selectDiscussPostRows(int userId);
}
