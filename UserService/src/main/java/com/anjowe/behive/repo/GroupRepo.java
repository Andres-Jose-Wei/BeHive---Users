package com.anjowe.behive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.Group;

@Repository
public interface GroupRepo extends ReactiveCrudRepository<Group,String>{

}
