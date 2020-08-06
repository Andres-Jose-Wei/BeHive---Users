package com.anjowe.behive.service;

import java.util.Map;

import com.anjowe.behive.model.Review;

import reactor.core.publisher.Mono;

public interface RatingService {
	public Mono<Boolean> reviewAndRateUser(String usernameReviewee, String usernameReviewer, Review review, Map<String, Double> skillRating, double punctuality);
}
