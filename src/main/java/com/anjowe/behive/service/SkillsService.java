package com.anjowe.behive.service;

import java.util.List;

import com.anjowe.behive.model.Skill;

import reactor.core.publisher.Mono;

public interface SkillsService {
	public Mono<List<String>> getAllSkills();

	public boolean addSkill(Skill skill);

	public boolean deleteSkill(Skill skill);

	public Mono<Boolean> userAddSkill(Skill skill, String username);

	public boolean userDeleteSkill(Skill skill, String username);
}
