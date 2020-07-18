package com.anjowe.behive.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.anjowe.behive.model.Skill;
import com.anjowe.behive.model.User;
import com.anjowe.behive.repo.UserRepo;

public class RatingServiceImpl implements RatingService {

	private UserService userService;
	
	private UserRepo userRepo;
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private int maxProjectCount = userRepo.findFirstByOrderByProjectCountDesc().getProjectCount();
	
	private int maxUniqueReviewersCount = userRepo.findFirstByOrderByUniqueReviewersCountDesc().getUniqueReviewersCount();
	
	private int maxMvpCount = userRepo.findFirstByOrderByMvpCountDesc().getMvpCount();
	
	@Override
	public boolean rateUserSkills(String username, Map<String, Double> skillRating) {
		User user = userService.getUser(username);
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
		userService.updateUser(user);
		return true;
	}

	public boolean ratePersonalSkills(String username, double punctuality) {
		User user = userService.getUser(username);
		user.setPersonalSkillAvg((user.getUniqueReviewersCount()/maxUniqueReviewersCount)*100 + punctuality + (user.getMvpCount()/maxMvpCount)*100);
		userService.updateUser(user);
		return true;
	}

	public boolean calculateOverallAverage(String username) {
		User user = userService.getUser(username);
		user.setOverallRating((user.getTechnicalSkillAvg() + user.getPersonalSkillAvg()) / 2);
		return true;
	}





}
