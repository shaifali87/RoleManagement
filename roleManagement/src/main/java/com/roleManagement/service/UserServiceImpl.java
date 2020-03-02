package com.roleManagement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.roleManagement.Entity.Role;
import com.roleManagement.Entity.User;
import com.roleManagement.Repository.RoleRepository;
import com.roleManagement.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("USER"));
		if ("ADMIN".equalsIgnoreCase(user.getAuthority())) {
			roles.add(roleRepository.findByName("ADMIN"));
		}

		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findByAuthority("user");
	}

	@Override
	public List<User> getAllAdmins() {
		return userRepository.findByAuthority("admin");
	}

}