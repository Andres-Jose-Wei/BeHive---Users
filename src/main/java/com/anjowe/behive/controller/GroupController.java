package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/group")
	public boolean adminAddGroup(@RequestBody List<String> groups) {
		for(String groupName: groups) {
			groupService.addGroup(new Group(groupName));
		}
		return true;
	}

	@DeleteMapping("/group")
	public boolean adminDeletePosition(@RequestBody List<String> groups) {
		for(String groupName: groups) {
			groupService.deleteGroup(new Group(groupName));
		}
		return true;
	}
}
