package com.anjowe.behive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.User;
import com.anjowe.behive.repo.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	User user = new User();
	private UserRepo userRepo;

	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public Flux<User> getAllUsers() {
		return this.userRepo.findAll();
	}
	
	@Override
	public Mono<User> getUser(String username) {
		return this.userRepo.findByUsername(username);
	}

	@Override
	public boolean createOrSaveUser(User user) {
		user.setAvailable(true);
		this.userRepo.save(user).subscribe();
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		this.userRepo.delete(user).subscribe();
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		this.userRepo.save(user).subscribe();
		return true;
	}

}
