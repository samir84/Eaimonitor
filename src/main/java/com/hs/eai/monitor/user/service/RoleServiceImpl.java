package com.hs.eai.monitor.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.eai.monitor.user.dao.RoleDao;
import com.hs.eai.monitor.user.model.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return roleDao.findByName(roleName);
	}

}
