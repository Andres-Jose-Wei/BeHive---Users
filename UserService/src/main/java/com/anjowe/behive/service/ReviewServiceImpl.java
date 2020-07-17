package com.anjowe.behive.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Review;
import com.anjowe.behive.model.User;
import com.anjowe.behive.repo.UserRepo;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean addReview(String usernameReviewee, String usernameReviewer, Review review) {
		User user = this.userService.getUser(usernameReviewee);
		//
		return true;
	}

	@Override
	public boolean updateReview(String usernameReviewee, String usernameReviewer, Review review) {

		return true;
	}

	@Override
	public boolean countReviews(String username) {
		User user = this.userService.getUser(username);
		for(String key: user.getReviews().keySet()) {
			user.setReviewsCount(user.getReviewsCount()+user.getReviews().get(key).size());
		}
		return this.userService.updateUser(user);
	}

	@Override
	public boolean countUniqueReviewers(String username) {
		User user = this.userService.getUser(username);
		user.setUniqueReviewersCount(user.getReviews().keySet().size());
		return this.userService.updateUser(user);
	}

}
