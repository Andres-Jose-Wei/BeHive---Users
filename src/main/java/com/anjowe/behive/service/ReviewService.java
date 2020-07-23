package com.anjowe.behive.service;

import com.anjowe.behive.model.Review;

public interface ReviewService {

	public boolean addReview(String usernameReviewee, String usernameReviewer, Review review);
	
	public boolean countReviews(String username);
	
	public boolean countUniqueReviewers(String username);

}
