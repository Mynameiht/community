package com.newcoder.community;

import com.newcoder.community.dao.AlphaDao;
import com.newcoder.community.dao.DiscussPostMapper;
import com.newcoder.community.dao.UserMapper;
import com.newcoder.community.entity.DiscussPost;
import com.newcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void applicationTest(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
		alphaDao = (AlphaDao) applicationContext.getBean("hibernate");
		System.out.println(alphaDao.select());
	}


	@Test
	public void simpleDataFormat(){
		SimpleDateFormat bean = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(bean.format(new Date()));
	}
	@Autowired
	private UserMapper userMapper;
	@Test
	public void selectTest(){
		User user = userMapper.selectById(101);
		System.out.println(user);
	}

	//添加测试
	@Test
	public void insertTest(){
		User user = new User();
		user.setUsername("wangwu");
		user.setPassword("123");
		user.setEmail("wangwu@email.com");
		user.setStatus(0);
		user.setType(1);
		user.setCreateTime(new Date());
		userMapper.insertUser(user);
	}
	@Autowired
	private DiscussPostMapper discussPostMapper;
	@Test
	public void discussPostTest(){
		List<DiscussPost> list = discussPostMapper.selectDiscussPosts("149",5,0);
		for (DiscussPost discussPost : list) {
			System.out.println(discussPost);
		}

		int i = discussPostMapper.selectDiscussPostRows("149");
		System.out.println(i);
	}
}
