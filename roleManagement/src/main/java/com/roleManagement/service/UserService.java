package com.roleManagement.service;

import java.util.List;

import com.roleManagement.Entity.User;

public interface UserService {
	
	void save(User user);
	
	User findByUsername(String username);
	
	List<User> getAllUsers();
	
	List<User> getAllAdmins();
	
}
