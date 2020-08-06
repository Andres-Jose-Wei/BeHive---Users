package com.anjowe.behive.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Review;

import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Mono<Boolean> addReview(String usernameReviewee, String usernameReviewer, Review review) {
		return this.userService.getUser(usernameReviewee).map(user -> {
				List<Review> tempReviewList = user.getReviews().get(usernameReviewer);
				tempReviewList.add(review);
				Map <String, List<Review>> tempReviewsMap = user.getReviews();
				tempReviewsMap.put(usernameReviewer, tempReviewList);
				user.setReviews(tempReviewsMap);
				user.setUniqueReviewersCount(user.getReviews().keySet().size());
				this.userService.updateUser(user);
				//countUniqueReviewers(usernameReviewee);
				return true;
		});
	}
	
	/*
	private Mono<Boolean> countUniqueReviewers(String username) {
		return this.userService.getUser(username).map(user ->{
		user.setUniqueReviewersCount(user.getReviews().keySet().size());
		this.userService.updateUser(user);
		return true;
		});
	}
	*/
	
	@Override
	public Mono<Map<String, List<Review>>> getUserReviews(String username) {
		return this.userService.getUser(username).map(user -> {
			return user.getReviews();
		});
	}

}
