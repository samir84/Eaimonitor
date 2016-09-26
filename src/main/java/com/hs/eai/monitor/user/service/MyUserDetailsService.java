package com.hs.eai.monitor.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.eai.monitor.user.dao.UserDao;
import com.hs.eai.monitor.user.model.Role;
import com.hs.eai.monitor.user.model.User;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userRepositoryImpl;


    public MyUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String username)  {
    	 
    	User user  = null;
		try {
			user = userRepositoryImpl.findByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if (user != null) {
    		
    		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),getAuthorities(user.getRoles()));
    		return userDetails;
    		
    	}else{
    		throw new UsernameNotFoundException("No user with username " + username + " found!");
    	}
    	
    }

   
    
    private List<GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        //you can also add different roles here
        //for example, the user is also an admin of the site, then you can add ROLE_ADMIN
        //so that he can view pages that are ROLE_ADMIN specific
        
        for(Role role : roles){
        	if (role != null && role.getName().trim().length() > 0){
        		authList.add(new SimpleGrantedAuthority(role.getName()));
        	}
        }
        return authList;
    }

  
}
