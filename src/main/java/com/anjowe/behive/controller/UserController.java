package com.anjowe.behive.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Group;
import com.anjowe.behive.model.Position;
import com.anjowe.behive.model.User;
import com.anjowe.behive.service.GroupService;
import com.anjowe.behive.service.PositionService;
import com.anjowe.behive.service.SkillsService;
import com.anjowe.behive.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	private PositionService positionService;
	private GroupService groupService;
	private SkillsService skillsService;
	private UserService userService;

	public UserController(PositionService positionService, GroupService groupService, SkillsService skillsService,
			UserService userService){
		super();
		this.positionService = positionService;
		this.groupService = groupService;
		this.skillsService = skillsService;
		this.userService = userService;
	}

	@GetMapping("/users")
	public Flux<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/available")
	public Flux<User> getAllAvailableUsers(){
		return userService.getAllAvailableUsers();
	}

	@GetMapping("/user")
	public Mono<User> getAccountUser(@RequestHeader("USER_NAME") String username){
		return userService.getUser(username);
	}
	
	@GetMapping("/user/{username}")
	public Mono<User> getUser(@PathVariable("username") String username){
		return userService.getUser(username);
	}

	@PostMapping("/user")
	public boolean updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}

	@PostMapping("/user/positions/{positionName}")
	public Mono<Boolean> userAddPosition(@PathVariable("positionName") String positionName, @RequestHeader("USER_NAME") String username){
		return positionService.userAddPosition(new Position(positionName), username);
	}

	@DeleteMapping("/user/positions/{positionName}")
	public Mono<Boolean> userDeletePosition(@PathVariable("positionName") String positionName, @RequestHeader("USER_NAME") String username){
		return positionService.userDeletePosition(new Position(positionName), username);
	}

	@PostMapping("/user/groups/{groupName}")
	public Mono<Boolean> userAddGroup(@PathVariable("groupName") String groupName, @RequestHeader("USER_NAME") String username){
		return groupService.userAddOrUpdateGroup(new Group(groupName), username);
	}

	@DeleteMapping("/user/groups/{groupName}")
	public Mono<Boolean> userDeleteGroup(@PathVariable("groupName") String groupName, @RequestHeader("USER_NAME") String username){
		return groupService.userDeleteGroup(new Group(groupName), username);
	}

	@PostMapping("/user/skills/{skill}")
	public Mono<Boolean> userAddSkill(@PathVariable(name = "skill") String skill, @RequestHeader("USER_NAME") String username){
		return skillsService.userAddSkill(skill, username);
	}

	@DeleteMapping("/user/skills/{skill}")
	public Mono<Boolean> userDeleteSkill(@PathVariable(name = "skill") String skill, @RequestHeader("USER_NAME") String username){
		return skillsService.userDeleteSkill(skill, username);
	}
	
	@PostMapping("/user/{username}/availability/{isAvailable}")
	public Mono<Boolean> setUserAvailability(@PathVariable(name = "isAvailable") boolean availability, @PathVariable("username") String username){
		return this.userService.setUserAvailability(username, availability);
	}

}
