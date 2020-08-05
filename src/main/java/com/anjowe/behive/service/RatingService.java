package com.anjowe.behive.service;

import java.util.Map;

import reactor.core.publisher.Mono;

public interface RatingService {
	public Mono<Boolean> rateTechnicalSkills(String username, Map<String, Double> skillRating);

	public Mono<Boolean> ratePersonalSkills(String username, double punctuality);
}
