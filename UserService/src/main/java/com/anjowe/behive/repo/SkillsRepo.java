package com.anjowe.behive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.anjowe.behive.model.Skill;

@Repository
public interface SkillsRepo extends ReactiveCrudRepository<Skill,String>{

}
