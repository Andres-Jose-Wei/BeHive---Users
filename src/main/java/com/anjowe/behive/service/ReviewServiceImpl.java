package com.anjowe.behive.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean addReview(String usernameReviewee, String usernameReviewer, Review review) {
		this.userService.getUser(usernameReviewee).map(user -> {
			if(user.getReviews()==null) {
				Map<String, List<Review>> reviews = new HashMap<String, List<Review>>();
				reviews.put(usernameReviewer, Arrays.asList(new Review[] {review}));
				user.setReviews(reviews);
				return this.userService.updateUser(user);
			}else {
				List<Review> tempReviewList = user.getReviews().get(usernameReviewer);
				tempReviewList.add(review);
				Map <String, List<Review>> tempReviewsMap = user.getReviews();
				tempReviewsMap.put(usernameReviewer, tempReviewList);
				this.userService.updateUser(user);
				countUniqueReviewers(usernameReviewee);
				this.userService.updateUser(user);
				return true;
			}
		}).subscribe();
		return true;
	}
	
	@Override
	public boolean countReviews(String username) {
		this.userService.getUser(username).map(user -> {
			if(user.getReviews()==null) {
				user.setReviews(new HashMap<String, List<Review>>());
			}
			for(String key: user.getReviews().keySet()) {
				user.setReviewsCount(user.getReviewsCount()+user.getReviews().get(key).size());
			}
			this.userService.updateUser(user);
			return true;
		}).subscribe();
		return true;
	}

	@Override
	public boolean countUniqueReviewers(String username) {
		this.userService.getUser(username).map(user ->{
		if(user.getReviews()==null) {
			user.setReviews(new HashMap<String, List<Review>>());
		}
		user.setUniqueReviewersCount(user.getReviews().keySet().size());
		this.userService.updateUser(user);
		return true;
		}).subscribe();
		return true;
	}

}
