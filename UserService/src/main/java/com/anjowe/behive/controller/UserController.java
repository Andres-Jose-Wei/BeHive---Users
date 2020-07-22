package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Group;
import com.anjowe.behive.model.Position;
import com.anjowe.behive.model.Skill;
import com.anjowe.behive.model.User;
import com.anjowe.behive.service.GroupService;
import com.anjowe.behive.service.PositionService;
import com.anjowe.behive.service.SkillsService;
import com.anjowe.behive.service.UserService;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
public class UserController {

	private PositionService positionService;
	private GroupService groupService;
	private SkillsService skillsService;
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@Autowired
	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}
	
	
	@GetMapping("/user")
	public Flux<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("user/{username}/position/{positionName}")
	public boolean userAddPosition(@PathVariable("positionName") String positionName, @PathVariable("username") String username) {
		return positionService.userAddPosition(new Position(positionName), username);
	}

	@DeleteMapping("user/{username}/position/{positionName}")
	public boolean userDeletePosition(@PathVariable("positionName") String positionName, @PathVariable("username") String username) {
		return positionService.userDeletePosition(new Position(positionName), username);
	}
	
	@PostMapping("user/{username}/group/{groupName}")
	public boolean userAddGroup(@PathVariable("groupName") String groupName, @PathVariable("username") String username) {
		return groupService.userAddOrUpdateGroup(new Group(groupName), username);
	}

	@DeleteMapping("user/{username}/group/{groupName}")
	public boolean userDeleteGroup(@PathVariable("groupName") String groupName, @PathVariable("username") String username) {
		return groupService.userDeleteGroup(new Group(groupName), username);
	}
	
	@PostMapping("user/{username}/skills")
	public boolean userAddSkill(@RequestBody List<String> skills, @PathVariable("username") String username) {
		for(String skillName: skills) {
			skillsService.userAddSkill(new Skill(skillName), username);
		}
		return true;
	}

	@DeleteMapping("user/{username}/skills")
	public boolean userDeleteSkill(@RequestBody List<String> skills, @PathVariable("username") String username) {
		for(String skillName: skills) {
			skillsService.userDeleteSkill(new Skill(skillName), username);
		}
		return true;
	}
	
	
	
	
}
