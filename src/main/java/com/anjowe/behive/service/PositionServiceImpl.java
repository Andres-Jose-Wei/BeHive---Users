package com.anjowe.behive.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anjowe.behive.logger.AppLogger;
import com.anjowe.behive.model.Position;
import com.anjowe.behive.repo.PositionRepo;

import reactor.core.publisher.Mono;

@Service
public class PositionServiceImpl implements PositionService {

	private PositionRepo positionRepo;
	private UserService userService;
	
	public PositionServiceImpl(PositionRepo positionRepo, UserService userService) {
		super();
		this.positionRepo = positionRepo;
		this.userService = userService;
	}

	@Override
	public Mono<List<String>> getAllPositions() {
		return this.positionRepo.findAll().map(position -> position.getName()).collect(Collectors.toList());
	}

	@Override
	public boolean addPosition(Position position) {
		
		this.positionRepo.save(position).subscribe();
		
		System.out.println("Position ("+position.toString()+"): saved");
		AppLogger.log.info("Position ("+position.toString()+"): saved");
		return true;
	}

	@Override
	public boolean deletePosition(Position position) {
		this.positionRepo.delete(position).subscribe();
		
		System.out.println("Position ("+position.toString()+"): deleted");
		AppLogger.log.info("Position ("+position.toString()+"): deleted");
		return true;
	}
	
	@Override
	public Mono<Boolean> userAddPosition(Position position, String username) {
		return this.userService.getUser(username).map(user ->{
			user.setPosition(position);
			this.userService.updateUser(user);
			
			System.out.println("Position ("+position.toString()+"): added to User ("+username+")");
			AppLogger.log.info("Position ("+position.toString()+"): added to User ("+username+")");
			return true;
		});
	}

	@Override
	public Mono<Boolean> userDeletePosition(Position position, String username) {
		return this.userService.getUser(username).map(user ->{
			user.setPosition(null);
			this.userService.updateUser(user);
			
			System.out.println("Position ("+position.toString()+"): deleted from User ("+username+")");
			AppLogger.log.info("Position ("+position.toString()+"): deleted from User ("+username+")");
			return true;
		});
	}

}
