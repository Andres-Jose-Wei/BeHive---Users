package com.anjowe.behive.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.Skill;

@Repository
public interface SkillsRepo extends ReactiveMongoRepository<Skill,String>{

}
