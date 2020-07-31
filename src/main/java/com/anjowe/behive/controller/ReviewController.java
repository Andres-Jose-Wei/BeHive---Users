package com.anjowe.behive.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@PostMapping("/user/review/{revieweeUsername}")
	public Mono<Boolean> addReview(@RequestBody Review review, @RequestHeader("USER_NAME") String reviewerUsername, @PathVariable("revieweeUsername") String revieweeUsername ) {
		return this.reviewService.addReview(revieweeUsername, reviewerUsername, review);
	}
	
	@GetMapping("/reviews/{revieweeUsername}")
	public Mono<Map<String, List<Review>>> getUserReviews(@PathVariable("revieweeUsername") String revieweeUsername ){
		return this.reviewService.getUserReviews(revieweeUsername);
	} 
}
