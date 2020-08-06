package com.anjowe.behive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.dto.RatingFormContent;
import com.anjowe.behive.service.RatingService;

import reactor.core.publisher.Mono;

@RestController
public class RatingController {

	private RatingService ratingService;
	
	@Autowired
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@PostMapping("/user/rate/{username}/punctuality/{punctuality}")
	public Mono<Boolean> reviewAndRateUser(@PathVariable("username") String usernameReviewee, @RequestHeader("USER_NAME") String usernameReviewer, @RequestBody RatingFormContent formContent, @PathVariable("punctuality") double punctuality){
		return this.ratingService.reviewAndRateUser(usernameReviewee, usernameReviewer, formContent.getReview(), formContent.getSkillRating(), punctuality);
	}
	
	
}
