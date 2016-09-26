package com.hs.eai.monitor.user.service;

import java.util.List;

import com.hs.eai.monitor.user.model.User;


public interface UserService {

	User findById(Integer id);
	User findByUsername(String username);
	User findByEmail(String email);
	List<User> findByRole(String role);
	void save(User user);
	void update(User user);
	void delete(Integer id);
    List<User> findAll();

    void createPasswordResetTokenForUser(User user, String token);
    public String validatePasswordResetToken(long id, String token);
    public boolean checkIfValidOldPassword( User user,  String oldPassword);
    public void changeUserPassword(final User user, final String password);
}
