package com.financas.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	
	private static final String ADMIN = "admin";
	private static final String USER = "marcus";

	public static void main(String[] args) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		System.out.println(ADMIN);
		System.out.println(enconder.encode(ADMIN));
		
		System.out.println(USER);
		System.out.println(enconder.encode(USER));
	}

}

