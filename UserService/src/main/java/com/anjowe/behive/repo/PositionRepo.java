package com.anjowe.behive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.Position;

@Repository
public interface PositionRepo extends ReactiveCrudRepository<Position,String>{

}