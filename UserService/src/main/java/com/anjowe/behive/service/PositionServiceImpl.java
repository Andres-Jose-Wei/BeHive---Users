package com.anjowe.behive.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Position;
import com.anjowe.behive.model.User;
import com.anjowe.behive.repo.PositionRepo;

import reactor.core.publisher.Mono;

@Service
public class PositionServiceImpl implements PositionService {

	private PositionRepo positionRepo;
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setPositionRepo(PositionRepo positionRepo) {
		this.positionRepo = positionRepo;
	}

	@Override
	public Mono<List<String>> getAllPositions() {
		return this.positionRepo.findAll().map(position -> position.getName()).collect(Collectors.toList());
	}

	@Override
	public boolean addPosition(Position position) {
		
		this.positionRepo.save(position).subscribe();
		System.out.println("Position ("+position.toString()+"): saved");
		return true;
	}

	@Override
	public boolean deletePosition(Position position) {
		this.positionRepo.delete(position).subscribe();
		System.out.println("Position ("+position.toString()+"): deleted");
		return true;
	}
	
	@Override
	public boolean userAddPosition(Position position, String username) {
		User user = this.userService.getUser(username);
		user.setPosition(position);
		System.out.println("Position ("+position.toString()+"): added to User ("+username+")");
		this.userService.updateUser(user);
		System.out.println("User ("+username+"): updated");
		return true;
	}

	@Override
	public boolean userDeletePosition(Position position, String username) {
		User user = this.userService.getUser(username);
		user.setPosition(null);
		System.out.println("Position ("+position.toString()+"): deleted from User ("+username+")");
		this.userService.updateUser(user);
		System.out.println("User ("+username+"): updated");
		return true;
	}

}
