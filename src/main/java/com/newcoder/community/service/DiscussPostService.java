package com.newcoder.community.service;

import com.newcoder.community.entity.DiscussPost;

import java.util.List;

public interface DiscussPostService {

    List<DiscussPost> findDiscussPosts(String userId,int limit,int offset);
    int findDiscussPostRows(String userId);
}
