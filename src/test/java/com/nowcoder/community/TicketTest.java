package com.nowcoder.community;


import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.pojo.LoginTicket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest(classes = CommunityApplication.class)
public class TicketTest {
    @Resource
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void ticketTest(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(123);
        loginTicket.setTicket("12334534");
        loginTicket.setStatus(1);
        loginTicket.setExpired(new Date());
        int i = loginTicketMapper.insertLoginTicket(loginTicket);
        System.out.println(i);

        LoginTicket loginTicket1 = loginTicketMapper.selectByTicket(loginTicket.getTicket());
        System.out.println(loginTicket1);

        loginTicketMapper.updateStatus(loginTicket1.getTicket(),0);
    }

}
