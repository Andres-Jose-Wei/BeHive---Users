package com.anjowe.behive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.anjowe.behive.model.Skill;
import com.anjowe.behive.model.User;
import com.anjowe.behive.repo.SkillsRepo;

import reactor.core.publisher.Mono;

public class SkillsServiceImpl implements SkillsService {
	private SkillsRepo skillsRepo;
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setSkillsRepo(SkillsRepo skillsRepo) {
		this.skillsRepo = skillsRepo;
	}

	@Override
	public Mono<List<String>> getAllSkills() {
		return this.skillsRepo.findAll().map(skill -> skill.getName()).collect(Collectors.toList());
	}

	@Override
	public boolean addSkill(Skill skill) {
		this.skillsRepo.save(skill).subscribe();
		System.out.println("Skill ("+skill.toString()+"): saved");
		return true;
	}

	@Override
	public boolean deleteSkill(Skill skill) {
		this.skillsRepo.delete(skill).subscribe();
		System.out.println("Skill ("+skill.toString()+"): deleted");
		return true;
	}

	@Override
	public boolean userAddSkill(Skill skill, String username) {
		User user = this.userService.getUser(username);
		Map<Skill, List<Double>> tempSkillRatings = user.getSkillRatings();
		Map<Skill, Double> tempSkillStats = user.getSkillStats();
		tempSkillRatings.put(skill, new ArrayList<Double>());
		tempSkillStats.put(skill, 0.0d);
		user.setSkillRatings(tempSkillRatings);
		user.setSkillStats(tempSkillStats);
		System.out.println("Skill ("+skill.toString()+"): added to User ("+username+")");
		this.userService.updateUser(user);
		System.out.println("User ("+username+"): updated");
		return true;
	}

	@Override
	public boolean userDeleteSkill(Skill skill, String username) {
		User user = this.userService.getUser(username);
		Map<Skill, List<Double>> tempSkillRatings = user.getSkillRatings();
		Map<Skill, Double> tempSkillStats = user.getSkillStats();
		tempSkillRatings.remove(skill);
		tempSkillStats.remove(skill);
		this.userService.updateUser(user);
		System.out.println("Skill ("+skill.toString()+"): deleted from User ("+username+")");
		this.userService.updateUser(user);
		System.out.println("User ("+username+"): updated");
		return true;
	}

}
