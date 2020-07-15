package com.anjowe.behive.service;

import com.anjowe.behive.model.Review;

public interface ReviewService {

	public boolean createOrSaveReview(Review review);

	public boolean deleteReview(Review review);

	public boolean updateReview(Review review);
}
