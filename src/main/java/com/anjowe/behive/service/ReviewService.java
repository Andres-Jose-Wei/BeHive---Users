package com.anjowe.behive.service;

import java.util.List;
import java.util.Map;

import com.anjowe.behive.model.Review;

import reactor.core.publisher.Mono;

public interface ReviewService {

	public Mono<Boolean> addReview(String usernameReviewee, String usernameReviewer, Review review);
	
	public Mono<Map<String,List<Review>>> getUserReviews(String username);

}
