package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Skill;
import com.anjowe.behive.service.SkillsService;

import reactor.core.publisher.Mono;

@RestController
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
	public boolean adminAddSkill(@RequestBody List<String> skills){
		for(String skillName: skills) {
			skillsService.addSkill(new Skill(skillName));
		}
		return true;
	}
	
	@DeleteMapping("/skill/{skillName}")
	public boolean adminDeleteSkill(@RequestBody List<String> skills){
		for(String skillName: skills) {
			skillsService.deleteSkill(new Skill(skillName));
		}
		return true;
	}

	
	

}
