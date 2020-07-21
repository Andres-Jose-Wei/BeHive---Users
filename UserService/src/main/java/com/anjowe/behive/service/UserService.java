package com.anjowe.behive.service;

import com.anjowe.behive.model.User;

import reactor.core.publisher.Flux;

public interface UserService {
	
	Flux<User> getAllUsers();
	
	public User getUser(String username);

	public boolean createOrSaveUser(User user);

	public boolean deleteUser(User user);

	public boolean updateUser(User user);
	
	
	
}
