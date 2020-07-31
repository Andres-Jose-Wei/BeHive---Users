package com.anjowe.behive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.logger.AppLogger;
import com.anjowe.behive.model.User;
import com.anjowe.behive.repo.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	User user;
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
	public Flux<User> getAllAvailableUsers() {
		return this.userRepo.findByIsAvailable(true);
	}
	
	@Override
	public Mono<User> getUser(String username) {
		return this.userRepo.findByUsername(username);
	}

	@Override
	public boolean createOrSaveUser(User user) {
		user.setAvailable(true);
		this.userRepo.save(user).subscribe();
		System.out.println("User (" + user.getUsername() + "): registered and saved into database");
		AppLogger.log.info("User (" + user.getUsername() + "): registered and saved into database");
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		this.userRepo.delete(user).subscribe();
		System.out.println("User (" + user.getUsername() + "): deleted");
		AppLogger.log.info("User (" + user.getUsername() + "): deleted");
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		this.userRepo.save(user).subscribe();
		System.out.println("User (" + user.getUsername() + "): updated");
		AppLogger.log.info("User (" + user.getUsername() + "): updated");
		return true;
	}
	
	@Override
	public Mono<Boolean> setUserAvailability(String username, boolean availability) {
		return getUser(username).map(user ->{
			user.setAvailable(availability);
			this.userRepo.save(user).subscribe();
			if(availability == false) {
				System.out.println("User (" + user.getUsername() + "): availibity set to false");
				AppLogger.log.info("User (" + user.getUsername() + "): availibity set to false");
			}
			System.out.println("User (" + user.getUsername() + "): availibity set to true");
			AppLogger.log.info("User (" + user.getUsername() + "): availibity set to true");
			return true;
		});
	}
	
	
	
	

}
