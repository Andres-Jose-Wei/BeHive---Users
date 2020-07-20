package com.anjowe.behive.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.Position;
import com.anjowe.behive.repo.PositionRepo;

import reactor.core.publisher.Mono;

@Service
public class PositionServiceImpl implements PositionService {

	private PositionRepo positionRepo;

	@Autowired
	public void setPositionRepo(PositionRepo positionRepo) {
		this.positionRepo = positionRepo;
	}

	@Override
	public Mono<List<String>> getAllPositions() {
		return this.positionRepo.findAll().map(position -> position.getName()).collect(Collectors.toList());
	}

	@Override
	public boolean addPosition(Position position) {
		this.positionRepo.save(position).subscribe();
		System.out.println("saved");
		return true;
	}

	@Override
	public boolean deletePosition(Position position) {
		this.positionRepo.delete(position).subscribe();
		return true;
	}

}
