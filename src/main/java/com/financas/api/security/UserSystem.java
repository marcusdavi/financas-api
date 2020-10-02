package com.financas.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSystem extends User {

	private static final long serialVersionUID = 1L;

	private com.financas.api.model.User user;

	public UserSystem(com.financas.api.model.User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	public com.financas.api.model.User getUser() {
		return user;
	}

}
