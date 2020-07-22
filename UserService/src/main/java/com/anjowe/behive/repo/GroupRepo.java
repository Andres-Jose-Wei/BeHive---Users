package com.anjowe.behive.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.Group;

@Repository
public interface GroupRepo extends ReactiveMongoRepository<Group,String>{

}
