package com.anjowe.behive.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anjowe.behive.model.Skill;
import com.anjowe.behive.repo.UserRepo;

public class RatingServiceImpl implements RatingService {
	
	private int maxProjectCount;
	
	private int maxUniqueReviewersCount;
	
	private int maxMvpCount;
	
	private UserService userService;
	
	private UserRepo userRepo;
	
	public RatingServiceImpl(UserService userService, UserRepo userRepo) {
		this.userRepo = userRepo;
		this.userService = userService;
		
		this.userRepo.findFirstByOrderByProjectCountDesc().map(user -> {
			maxProjectCount = user.getProjectCount();
			return null;
		}).subscribe();
		
		this.userRepo.findFirstByOrderByUniqueReviewersCountDesc().map(user ->{
			maxUniqueReviewersCount = user.getUniqueReviewersCount();
			return null;
		}).subscribe();
		
		this.userRepo.findFirstByOrderByMvpCountDesc().map(user ->{
			maxMvpCount = user.getMvpCount();
			return null;
		}).subscribe();
	}
	
	
	@Override
	public boolean rateUserSkills(String username, Map<String, Double> skillRating) {
	this.userService.getUser(username).map(user -> {
			if (user.getSkillRatings() == null) {
				user.setSkillRatings(new HashMap<Skill, List<Double>>());
			}
			if (user.getSkillStats() == null) {
				user.setSkillStats(new HashMap<Skill, Double>());
			}

			double sumOfStats = 0.0d;
			for (String key : skillRating.keySet()) {
				user.getSkillRatings().get(new Skill(key)).add(skillRating.get(key));
				double avg = user.getSkillRatings().get(new Skill(key)).stream().mapToDouble(Double::doubleValue).average()
						.orElse(0.0);
				user.getSkillStats().put(new Skill(key), avg);
				sumOfStats += avg;
			}
			
			user.setTechnicalSkillAvg(((user.getProjectCount()/maxProjectCount)*100 + sumOfStats) / (user.getSkillStats().size() + 1));
			this.userService.updateUser(user);
			return true;
		}).subscribe();
		return true;
	}

	public boolean ratePersonalSkills(String username, double punctuality) {
		this.userService.getUser(username).map(user -> {
			user.setPersonalSkillAvg((user.getUniqueReviewersCount()/maxUniqueReviewersCount)*100 + punctuality + (user.getMvpCount()/maxMvpCount)*100);
			userService.updateUser(user);
			return true;
		}).subscribe();
		
		return true;
	}

	public boolean calculateOverallAverage(String username) {
		this.userService.getUser(username).map(user->{
			user.setOverallRating((user.getTechnicalSkillAvg() + user.getPersonalSkillAvg()) / 2);
			return true;
		}).subscribe();
		return true;
	}





}
