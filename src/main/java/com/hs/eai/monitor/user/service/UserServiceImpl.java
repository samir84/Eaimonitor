package com.hs.eai.monitor.user.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.eai.monitor.user.dao.PasswordResetTokenDao;
import com.hs.eai.monitor.user.dao.RoleDao;
import com.hs.eai.monitor.user.dao.UserDao;
import com.hs.eai.monitor.user.model.PasswordResetToken;
import com.hs.eai.monitor.user.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	PasswordResetTokenDao passwordTokenDao;
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
	public User findByUsername(String username) {
		
		User user = null ;
		try {
			user = userDao.findByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findByRole(String role) {
		
		return userDao.findByRole(role);
	}

	@Override
	@Transactional
	public void save(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(roleDao.findByName("ROLE_USER")));
		userDao.save(user);
		
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
		
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenDao.save(myToken);
		
	}
	@Override
    public String validatePasswordResetToken(long id, String token) {
        final PasswordResetToken passToken = passwordTokenDao.findByToken(token);
        if ((passToken == null) || (passToken.getUser().getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }

        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, userDetailsService.loadUserByUsername(user.getUsername()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }

	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}
	@Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userDao.update(user);
    }
	

}
