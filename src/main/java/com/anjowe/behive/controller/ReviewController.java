package com.anjowe.behive.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Review;
import com.anjowe.behive.service.ReviewService;

import reactor.core.publisher.Mono;

@RestController
public class ReviewController {
	private ReviewService reviewService;
	
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews/{revieweeUsername}")
	public Mono<Map<String, List<Review>>> getUserReviews(@PathVariable("revieweeUsername") String revieweeUsername ){
		return this.reviewService.getUserReviews(revieweeUsername);
	} 
}
