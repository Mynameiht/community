package com.newcoder.community.dao;

import com.newcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginTicketMapper {
    int insertLoginTicket(LoginTicket loginTicket);
    LoginTicket selectByTicket(String ticket);

    int updateStatus(String ticket,int status);
}
