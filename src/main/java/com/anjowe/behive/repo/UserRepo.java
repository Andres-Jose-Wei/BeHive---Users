package com.anjowe.behive.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveMongoRepository<User,String>{
	Mono<User> findFirstByOrderByProjectCountDesc();
	Mono<User> findFirstByOrderByUniqueReviewersCountDesc();
	Mono<User> findFirstByOrderByMvpCountDesc();
	Mono<User> findByUsername(String username);
	Flux<User> findByIsAvailable(boolean isAvailable);
}