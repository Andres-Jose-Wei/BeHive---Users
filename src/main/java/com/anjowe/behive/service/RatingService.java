package com.anjowe.behive.service;

import java.util.Map;

public interface RatingService {
	public boolean rateUserSkills(String username, Map<String, Double> skillRating);

	public boolean ratePersonalSkills(String username, double punctuality);

	public boolean calculateOverallAverage(String username);
}
