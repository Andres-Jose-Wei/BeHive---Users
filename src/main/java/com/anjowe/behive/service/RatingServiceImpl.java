package com.anjowe.behive.service;

import java.util.HashMap;
import java.util.List;
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
	}

	@Override
	public Mono<Boolean> rateUserSkills(String username, Map<String, Double> skillRating) {
		return this.userService.getUser(username).map(user -> {
			if (user.getSkillRatings() == null) {
				user.setSkillRatings(new HashMap<String, List<Double>>());
			}
			if (user.getSkillStats() == null) {
				user.setSkillStats(new HashMap<String, Double>());
			}

			double sumOfStats = 0.0d;
			for (String key : skillRating.keySet()) {
				user.getSkillRatings().get(key).add(skillRating.get(key));
				double avg = user.getSkillRatings().get(key).stream().mapToDouble(Double::doubleValue).average()
						.orElse(0.0);
				user.getSkillStats().put(key, avg);
				sumOfStats += avg;
			}

			user.setTechnicalSkillAvg(((user.getProjectCount() / maxProjectCount) * 100 + sumOfStats)
					/ (user.getSkillStats().size() + 1));
			this.userService.updateUser(user);
			return true;
		});
	}
	
	@Override
	public Mono<Boolean> ratePersonalSkills(String username, double punctuality) {
		return this.userService.getUser(username).map(user -> {
			user.setPersonalSkillAvg((user.getUniqueReviewersCount() / maxUniqueReviewersCount) * 100 + punctuality
					+ (user.getMvpCount() / maxMvpCount) * 100);
			userService.updateUser(user);
			return true;
		});
	}
	
	@Override
	public Mono<Boolean> calculateOverallAverage(String username) {
		return this.userService.getUser(username).map(user -> {
			user.setOverallRating((user.getTechnicalSkillAvg() + user.getPersonalSkillAvg()) / 2);
			return true;
		});
	}

}
