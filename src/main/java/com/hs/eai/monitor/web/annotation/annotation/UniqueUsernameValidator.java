package com.hs.eai.monitor.web.annotation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hs.eai.monitor.user.dao.UserDao;



public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	
	@Autowired
	private UserDao userRepository;

	public void initialize(UniqueUsername constraintAnnotation) {
	}

	public boolean isValid(String username, ConstraintValidatorContext context) {
		System.out.println("isValid Call");
		boolean isValid = false;
		try{
			userRepository.findByUsername(username);
		}catch(Exception exp){
			isValid = true;
		}
		return isValid;
		
	}

}
