package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Skill;
import com.anjowe.behive.service.SkillsService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class SkillsController {
	private SkillsService skillsService;
	
	@Autowired
	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}
	
	@GetMapping("/skill")
	public Mono<List<String>> getAllSkills(){
		return skillsService.getAllSkills();
	}
	
	@PostMapping("/skill/{skillName}")
	public boolean adminAddSkill(@PathVariable("skillName") String skillName){
		return skillsService.addSkill(new Skill(skillName));
	}
	
	@PostMapping("/skill/{skillName}")
	public boolean adminDeleteSkill(@PathVariable("skillName") String skillName){
		return skillsService.deleteSkill(new Skill(skillName));
	}

	
	

}
