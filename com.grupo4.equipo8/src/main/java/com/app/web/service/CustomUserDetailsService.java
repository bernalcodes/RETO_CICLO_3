package com.app.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.web.entity.User;
import com.app.web.repository.UserRepository;
import com.app.web.security.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("El nombre de usuario (" + username + ") no fue encontrado");
		}
		return new CustomUserDetails(user);
	}

}
