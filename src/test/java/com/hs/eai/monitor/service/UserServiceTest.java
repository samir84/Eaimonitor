package com.hs.eai.monitor.service;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hs.eai.monitor.user.model.User;
import com.hs.eai.monitor.user.service.UserService;
import com.hs.eai.monitor.spring.PersistenceJPAConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	
	@Test
	public void createUser(){
		
		User user = new User();
		user.setUsername("samir.elazzouzi");
		user.setPassword("tarazana");
		user.setEmail("elazzouzi.s@hotmail.com");
		
		userService.save(user);
	}
	@Test
	public void findById(){
		User user = new User();
		user.setUsername("test");
		user.setPassword("test");
		user.setEmail("test@hotmail.com");
		userService.save(user);
		userService.findById(user.getId());
	}
	@Test
	public void findAllTest(){
		
		List<User> users = userService.findAll();
		Assert.assertNotNull(users);
		for(User u : users){
			System.out.println(u.toString());
		}
	}
	@Test
	public void findByUsername(){
		User user = userService.findByUsername("Marcella Dilonardo");
		System.out.println(user.toString());
	}
	@Test
	public void findByEmail(){
		User user = userService.findByEmail("samir.elazzouzi@henryschein.nl");
		System.out.println(user.toString());
	}
	
}
