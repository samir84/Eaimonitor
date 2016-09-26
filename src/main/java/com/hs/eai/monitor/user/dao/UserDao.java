package com.hs.eai.monitor.user.dao;

import java.util.List;

import com.hs.eai.monitor.user.model.User;

public interface UserDao {

	
	void save(User user);
	void update(User user);
	void delete(Integer id);
	
	List<User> findAll();
	
	User findById(Integer id);
	
	User findByUsername(String username) throws Exception;

	User findByEmail(String email);
	List<User> findByRole(String role);

}
