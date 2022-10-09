package com.app.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.web.entity.Role;
import com.app.web.entity.User;
import com.app.web.repository.RoleRepository;
import com.app.web.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	public void saveUserWithDefaultRole(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		user.addRole(roleRepo.findByName("EMPLEADO"));

		userRepo.save(user);
	}

	public void saveUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		userRepo.save(user);
	}

	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User getUserById(Long id) {
		return userRepo.findById(id).get();
	}

	public List<Role> getRoles() {
		return roleRepo.findAll();
	}
}
