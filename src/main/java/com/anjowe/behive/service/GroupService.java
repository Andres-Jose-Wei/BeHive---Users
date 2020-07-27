package com.anjowe.behive.service;

import java.util.List;

import com.anjowe.behive.model.Group;

import reactor.core.publisher.Mono;

public interface GroupService {
	public Mono<List<String>> getAllGroups();
	
	public boolean addGroup(Group group);
	
	public boolean deleteGroup(Group group);
	
	public Mono<Boolean> userAddOrUpdateGroup(Group group, String username);
	
	public Mono<Boolean> userDeleteGroup(Group group, String username);
}
