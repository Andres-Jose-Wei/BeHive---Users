package com.anjowe.behive.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.Position;

@Repository
public interface PositionRepo extends ReactiveMongoRepository<Position, String> {

}