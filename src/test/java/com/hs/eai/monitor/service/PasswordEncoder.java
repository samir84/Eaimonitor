package com.hs.eai.monitor.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		String password = "tarazana";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println("hashedPassword:*"+hashedPassword+"*");
	}
}
