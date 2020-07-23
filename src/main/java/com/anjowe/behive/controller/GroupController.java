package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Group;
import com.anjowe.behive.service.GroupService;

import reactor.core.publisher.Mono;

@RestController
public class GroupController {
	private GroupService groupService;
	
	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@GetMapping("/group")
	public Mono<List<String>> getAllGroups(){
		return groupService.getAllGroups();
	}
	
	@PostMapping("/group/{groupName}")
	public boolean adminAddGroup(@PathVariable("groupName") String groupName) {
		return groupService.addGroup(new Group(groupName));
	}

	@DeleteMapping("group/{groupName}")
	public boolean adminDeletePosition(@PathVariable("groupName") String groupName) {
		return groupService.deleteGroup(new Group(groupName));
	}
}
