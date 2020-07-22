package com.anjowe.behive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Review;
import com.anjowe.behive.service.ReviewService;

@RestController
public class ReviewController {
	private ReviewService reviewService;
	
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PostMapping("review/{reviewerUsername}/add/{revieweeUsername}")
	public boolean addReview(@RequestBody Review review, @PathVariable("reviewerUsername") String reviewerUsername, @PathVariable("revieweeUsername") String revieweeUsername ) {
		return this.reviewService.addReview(revieweeUsername, reviewerUsername, review);
	}
}
