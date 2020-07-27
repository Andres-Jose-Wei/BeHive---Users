package com.anjowe.behive.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Group;
import com.anjowe.behive.repo.GroupRepo;

import reactor.core.publisher.Mono;

@Service
public class GroupServiceImpl implements GroupService {
	private GroupRepo groupRepo;
	private UserService userService;
	
	public GroupServiceImpl(GroupRepo groupRepo, UserService userService) {
		super();
		this.groupRepo = groupRepo;
		this.userService = userService;
	}

	@Override
	public Mono<List<String>> getAllGroups() {
		return this.groupRepo.findAll().map(group -> group.getName()).collect(Collectors.toList());
	}

	@Override
	public boolean addGroup(Group group) {
		this.groupRepo.save(group).subscribe();
		System.out.println("Group ("+group.toString()+"): saved");
		return true;
	}

	@Override
	public boolean deleteGroup(Group group) {
		this.groupRepo.delete(group).subscribe();
		System.out.println("Group ("+group.toString()+"): deleted");
		return true;
	}

	@Override
	public Mono<Boolean> userAddOrUpdateGroup(Group group, String username) {
		return this.userService.getUser(username).map(user ->{
			user.setGroup(group);
			System.out.println("Group ("+group.toString()+"): updated/added to User ("+username+")");
			this.userService.updateUser(user);
			System.out.println("User ("+username+"): updated");
			return true;
		});
	}

	@Override
	public Mono<Boolean> userDeleteGroup(Group group, String username) {
		return this.userService.getUser(username).map(user ->{
			user.setGroup(null);
			System.out.println("Group ("+group.toString()+"): deleted from User ("+username+")");
			this.userService.updateUser(user);
			System.out.println("User ("+username+"): updated");
			return true;
		});
	}


}
