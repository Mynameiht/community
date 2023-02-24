package com.newcoder.community;

import com.newcoder.community.dao.LoginTicketMapper;
import com.newcoder.community.entity.LoginTicket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoginTicketTest {

    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Test
    public void insertTest(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setTicket("2131234");
        loginTicket.setUserId("123");
        loginTicket.setStatus(1);
        loginTicket.setExpired(new Date());
        loginTicketMapper.insertLoginTicket(loginTicket);
    }


    @Test
    public void selectTest(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("2131234");
        System.out.println(loginTicket);
    }

    @Test
    public void updateTest(){

        loginTicketMapper.updateStatus("2131234",0);
    }
}
