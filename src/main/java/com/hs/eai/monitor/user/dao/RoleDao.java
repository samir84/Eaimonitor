package com.hs.eai.monitor.user.dao;


import com.hs.eai.monitor.user.model.Role;

public interface  RoleDao {

	Role findByName(String string);

}
