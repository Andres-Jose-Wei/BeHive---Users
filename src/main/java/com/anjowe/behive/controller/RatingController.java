package com.anjowe.behive.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.service.RatingService;

import reactor.core.publisher.Mono;

@RestController
public class RatingController {

	private RatingService ratingService;
	
	@Autowired
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@PostMapping("/user/rate/{username}/technicalskills")
	public Mono<Boolean> rateTechnicalSkills(@PathVariable("username") String username, @RequestBody Map<String, Double> skillRating){
		return this.ratingService.rateTechnicalSkills(username, skillRating);
	}

	@PostMapping("/user/rate/{username}/personalskills/{punctuality}")
	public Mono<Boolean> ratePersonalSkills(@PathVariable("username") String username, @PathVariable("punctuality") double punctuality){
		return this.ratingService.ratePersonalSkills(username, punctuality);
	}
	
	
}
