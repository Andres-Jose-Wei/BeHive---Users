package com.anjowe.behive.service;

import com.anjowe.behive.model.Review;

import reactor.core.publisher.Mono;

public interface ReviewService {

	public Mono<Boolean> addReview(String usernameReviewee, String usernameReviewer, Review review);
	
	public Mono<Boolean> countReviews(String username);
	
	public Mono<Boolean> countUniqueReviewers(String username);

}
