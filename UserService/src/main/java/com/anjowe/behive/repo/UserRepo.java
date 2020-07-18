package com.anjowe.behive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.User;

@Repository
public interface UserRepo extends ReactiveCrudRepository<User,String>{
	User findFirstByOrderByProjectCountDesc();
	User findFirstByOrderByUniqueReviewersCountDesc();
	User findFirstByOrderByMvpCountDesc();
}