package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void showApplicationFactory(){
        System.out.println(applicationContext);
    }

    @Test
    public void testAlphaDaoBean(){
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao);
        System.out.println(alphaDao.select());
        alphaDao = applicationContext.getBean("alphaDaoImpl",AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    public void testSimpleDateFormat(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
    }


    @Autowired
    @Qualifier("alphaDaoImpl")
    private AlphaDao alphaDao;

    @Test
    public void testAlphaDao(){
        System.out.println(alphaDao.select());
    }
    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateStatus(){
        userMapper.updateStatus(154, 0);
    }

    @Test
    public void updateHeader(){
        userMapper.updateHeader(154, "http://www.nowcoder.com/154.png");
    }

    @Test
    public void updatePassword(){
        userMapper.updatePassword(154,"654321");
    }

    @Test
    public void selectUser(){
        User user = userMapper.selectById(11);
        System.out.println(user);
    }

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void selectDiscussPosts(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 5);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
        int i = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(i);
    }

}
