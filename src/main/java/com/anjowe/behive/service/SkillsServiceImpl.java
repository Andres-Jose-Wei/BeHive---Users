package com.anjowe.behive.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anjowe.behive.logger.AppLogger;
import com.anjowe.behive.model.Skill;
import com.anjowe.behive.repo.SkillsRepo;

import reactor.core.publisher.Mono;

@Service
public class SkillsServiceImpl implements SkillsService {
	private SkillsRepo skillsRepo;
	private UserService userService;
	
	public SkillsServiceImpl(SkillsRepo skillsRepo, UserService userService) {
		super();
		this.skillsRepo = skillsRepo;
		this.userService = userService;
	}

	@Override
	public Mono<List<String>> getAllSkills() {
		return this.skillsRepo.findAll().map(skill -> skill.getName()).collect(Collectors.toList());
	}

	@Override
	public boolean addSkill(Skill skill) {
		this.skillsRepo.save(skill).subscribe();
		
		System.out.println("Skill (" + skill.toString() + "): saved");
		AppLogger.log.info("Skill (" + skill.toString() + "): saved");
		return true;
	}

	@Override
	public boolean deleteSkill(Skill skill) {
		this.skillsRepo.delete(skill).subscribe();
		
		System.out.println("Skill (" + skill.toString() + "): deleted");
		AppLogger.log.info("Skill (" + skill.toString() + "): deleted");
		return true;
	}

	@Override
	public Mono<Boolean> userAddSkill(String skill, String username) {
		return this.userService.getUser(username).map(user -> {
			//Map<String, List<Double>> tempSkillRatings = user.getSkillRatings();
			Map<String, Integer> tempNumSkillRatings = user.getNumSkillRatings();
			Map<String, Double> tempSkillStats = user.getSkillStats();
			//tempSkillRatings.put(skill, new ArrayList<Double>());
			tempNumSkillRatings.put(skill, 0);
			tempSkillStats.put(skill, 0.0d);
			//user.setSkillRatings(tempSkillRatings);
			user.setNumSkillRatings(tempNumSkillRatings);
			user.setSkillStats(tempSkillStats);
			this.userService.updateUser(user);
			
			System.out.println("Skill (" + skill + "): added to User (" + username + ")");
			AppLogger.log.info("Skill (" + skill + "): added to User (" + username + ")");
			return true;
		});
	}

	@Override
	public Mono<Boolean> userDeleteSkill(String skill, String username) {
		return this.userService.getUser(username).map(user -> {
			//Map<String, List<Double>> tempSkillRatings = user.getSkillRatings();
			Map<String, Integer> tempNumSkillRatings = user.getNumSkillRatings();
			Map<String, Double> tempSkillStats = user.getSkillStats();
			//tempSkillRatings.remove(skill);
			tempNumSkillRatings.remove(skill);
			tempSkillStats.remove(skill);
			//user.setSkillRatings(tempSkillRatings);
			user.setNumSkillRatings(tempNumSkillRatings);
			user.setSkillStats(tempSkillStats);
			this.userService.updateUser(user);
			
			System.out.println("Skill (" + skill + "): deleted from User (" + username + ")");
			AppLogger.log.info("Skill (" + skill + "): deleted from User (" + username + ")");
			return true;
		});
	}

}
