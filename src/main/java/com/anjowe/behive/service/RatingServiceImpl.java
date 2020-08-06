package com.anjowe.behive.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Review;
import com.anjowe.behive.repo.UserRepo;

import reactor.core.publisher.Mono;

@Service
public class RatingServiceImpl implements RatingService {
	private UserService userService;

	private UserRepo userRepo;
	
	private ReviewService reviewService;

	public RatingServiceImpl(UserService userService, UserRepo userRepo, ReviewService reviewService) {
		this.userRepo = userRepo;
		this.userService = userService;
		this.reviewService = reviewService;
	}

	@Override
	public Mono<Boolean> reviewAndRateUser(String usernameReviewee, String usernameReviewer, Review review, Map<String, Double> skillRating, double punctuality) {
		// Review the user (the reviewee)
		return this.reviewService.addReview(usernameReviewee, usernameReviewer, review).flatMap(data -> {
			// Retrieve MAX unique reviewers count, MAX project count, and MAX MVP count from database
			return getMaxUniqueReviewersCount().flatMap(maxUniqueReviewersCount -> {
				return getMaxProjectCount().flatMap(maxProjectCount -> {
					return getMaxMvpCount().flatMap( maxMvpCount -> {
						
						// Now, update the user's technical skills rating, personal skills rating and, thus, overall rating
						return this.userService.getUser(usernameReviewee).map(user -> {
							
							// Temp variables storing the updated punctuality count and updated punctuality average, respectively
							int tempPunctualityCount = user.getPunctualityCount() + 1;
							double tempPunctuality = user.getPunctuality()+(punctuality - user.getPunctuality())/tempPunctualityCount;
							
							// Instantiate MAX unique reviewers count from database
							int tempMaxUniqueReviewersCount = maxUniqueReviewersCount;
							if(tempMaxUniqueReviewersCount == 0) {
								tempMaxUniqueReviewersCount = 1;
							}
							
							// Instantiate MAX MVP count from database
							int tempMaxMvpCount = maxMvpCount;
							if(tempMaxMvpCount == 0) {
								tempMaxMvpCount = 1;
							}
							
							// Instantiate MAX project count from database
							int tempMaxProjectCount = maxProjectCount;
							if(tempMaxProjectCount == 0) {
								tempMaxProjectCount = 1;
							}
							
							
							//Update the user's punctuality count, punctuality avg, and personal skills avg
							user.setPunctualityCount(tempPunctualityCount);
							user.setPunctuality(tempPunctuality);
							user.setPersonalSkillAvg((user.getUniqueReviewersCount() / tempMaxUniqueReviewersCount) * 100 + tempPunctuality
									+ (user.getMvpCount() / tempMaxMvpCount) * 100);
						
							
							// Temp HashMaps storing current number of ratings for each skill and current average rating for each skill, respectively
							Map<String, Integer> tempNumSkillRatings = user.getNumSkillRatings();
							Map<String, Double> tempSkillStats = user.getSkillStats();
							
							// Temp variable that stores the sum of all the user's skill rating averages
							double sumOfStats = 0.0d;
							
							// For every skill that the user has
							for (String key : skillRating.keySet()) {
								
								// Increment the number of ratings for that skill
								tempNumSkillRatings.put(key, tempNumSkillRatings.get(key) + 1);
								// Calculate and update the new average rating for that skill
								double avg = tempSkillStats.get(key) + (skillRating.get(key) - tempSkillStats.get(key))/tempNumSkillRatings.get(key);
								tempSkillStats.put(key, avg);
								
								//Add the new average for that skill to the sum of all the user's skill rating averages
								sumOfStats += avg;
							}
							// Update the user's technical skill stats and technical skills avg rating
							user.setNumSkillRatings(tempNumSkillRatings);
							user.setSkillStats(tempSkillStats);
							user.setTechnicalSkillAvg(((user.getProjectCount() / tempMaxProjectCount) * 100 + sumOfStats)
									/ (user.getSkillStats().size() + 1));
							
							// Update the user's overall rating
							user.setOverallRating((user.getTechnicalSkillAvg() + user.getPersonalSkillAvg()) / 2);
							
							//Update the user
							this.userService.updateUser(user);
							return true;
						});
					});
				});
			});
		});
	}
	
	private Mono<Integer> getMaxProjectCount(){
		return this.userRepo.findFirstByOrderByProjectCountDesc().map(user -> {
			return user.getProjectCount();
		});
	}
	
	private Mono<Integer> getMaxMvpCount(){
		return this.userRepo.findFirstByOrderByMvpCountDesc().map(user -> {
			return user.getMvpCount();
		});
	}
	
	private Mono<Integer> getMaxUniqueReviewersCount(){
		return this.userRepo.findFirstByOrderByUniqueReviewersCountDesc().map(user -> {
			return user.getUniqueReviewersCount();
			
		});
	}

	
	
	

}
