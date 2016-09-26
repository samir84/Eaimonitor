package com.hs.eai.monitor.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hs.eai.monitor.spring.PersistenceJPAConfig;
import com.hs.eai.monitor.user.model.Role;
import com.hs.eai.monitor.user.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class RoleServiceTest {

	@Autowired
	RoleService roleService;
	
	@Test
	public void findRole(){
		
		Role roleUser = roleService.findRoleByName("USER_ROLE");
		Assert.assertNotNull(roleUser);
	}
}
