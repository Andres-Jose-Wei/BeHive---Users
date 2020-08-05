package com.anjowe.behive.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.anjowe.behive.repo.UserRepo;

import reactor.core.publisher.Mono;

@Service
public class RatingServiceImpl implements RatingService {

	private int maxProjectCount;

	private int maxUniqueReviewersCount;

	private int maxMvpCount;

	private UserService userService;

	private UserRepo userRepo;

	public RatingServiceImpl(UserService userService, UserRepo userRepo) {
		this.userRepo = userRepo;
		this.userService = userService;
		/*
		this.userRepo.findFirstByOrderByProjectCountDesc().map(user -> {
			maxProjectCount = user.getProjectCount();
			return null;
		}).subscribe();

		this.userRepo.findFirstByOrderByUniqueReviewersCountDesc().map(user -> {
			maxUniqueReviewersCount = user.getUniqueReviewersCount();
			return null;
		}).subscribe();

		this.userRepo.findFirstByOrderByMvpCountDesc().map(user -> {
			maxMvpCount = user.getMvpCount();
			return null;
		}).subscribe();
		*/
	}

	@Override
	public Mono<Boolean> rateTechnicalSkills(String username, Map<String, Double> skillRating) {
		
		// Instantiate MAX project count from database
		setMaxProjectCount();
		
		// Update a user's technical skills rating and overall rating
		return this.userService.getUser(username).map(user -> {
			// Temp HashMaps storing current number of ratings for each skill and current average rating for each skill, respectively
			Map<String, Integer> tempNumSkillRatings = user.getNumSkillRatings();
			Map<String, Double> tempSkillStats = user.getSkillStats();
			
			// Temp variable that stores the sum of all the user's skill rating averages
			double sumOfStats = 0.0d;
			
			// For every skill that a user has
			for (String key : skillRating.keySet()) {
				/*
				 * user.getSkillRatings().get(key).add(skillRating.get(key));
				 * double avg = user.getSkillRatings().get(key).stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
				 */
				
				// Increment the number of ratings for that skill
				tempNumSkillRatings.put(key, tempNumSkillRatings.get(key) + 1);
				// Calculate and update the new average rating for that skill
				double avg = tempSkillStats.get(key) + (skillRating.get(key) - tempSkillStats.get(key))/tempNumSkillRatings.get(key);
				tempSkillStats.put(key, avg);
				
				//Add the new average for that skill to the sum of all the user's skill rating averages
				sumOfStats += avg;
			}
			// Update user
			user.setNumSkillRatings(tempNumSkillRatings);
			user.setSkillStats(tempSkillStats);
			user.setTechnicalSkillAvg(((user.getProjectCount() / maxProjectCount) * 100 + sumOfStats)
					/ (user.getSkillStats().size() + 1));
			calculateOverallAverage(user.getUsername());
			
			this.userService.updateUser(user);
			return true;
		});
	}
	
	@Override
	public Mono<Boolean> ratePersonalSkills(String username, double punctuality) {
		// Instantiate MAX unique reviewers count from database
		setMaxUniqueReviewersCount();
		// Instantiate MAX MVP count from database
		setMaxMvpCount();
		
		// Update a user's personal skills rating and overall rating
		return this.userService.getUser(username).map(user -> {
			//Temp variables storing the updated punctuality count and updated punctuality average, respectively
			int tempPunctualityCount = user.getPunctualityCount() + 1;
			double tempPunctuality = user.getPunctuality()+(punctuality - user.getPunctuality())/tempPunctualityCount;
			
			// Update user
			user.setPunctualityCount(tempPunctualityCount);
			user.setPunctuality(tempPunctuality);
			user.setPersonalSkillAvg((user.getUniqueReviewersCount() / maxUniqueReviewersCount) * 100 + tempPunctuality
					+ (user.getMvpCount() / maxMvpCount) * 100);
			
			calculateOverallAverage(user.getUsername());
			
			this.userService.updateUser(user);
			return true;
		});
	}
	
	private Mono<Boolean> calculateOverallAverage(String username) {
		return this.userService.getUser(username).map(user -> {
			user.setOverallRating((user.getTechnicalSkillAvg() + user.getPersonalSkillAvg()) / 2);
			return true;
		});
	}
	
	private void setMaxProjectCount(){
		this.userRepo.findFirstByOrderByProjectCountDesc().map(user -> {
			maxProjectCount = user.getProjectCount();
			return null;
		}).subscribe();
	}
	
	private void setMaxMvpCount(){
		this.userRepo.findFirstByOrderByMvpCountDesc().map(user -> {
			maxMvpCount = user.getMvpCount();
			return null;
		}).subscribe();
	}
	
	private void setMaxUniqueReviewersCount(){
		this.userRepo.findFirstByOrderByUniqueReviewersCountDesc().map(user -> {
			maxUniqueReviewersCount = user.getUniqueReviewersCount();
			return null;
		}).subscribe();
	}

	
	
	

}
