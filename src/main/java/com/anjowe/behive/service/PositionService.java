package com.anjowe.behive.service;

import java.util.List;

import com.anjowe.behive.model.Position;

import reactor.core.publisher.Mono;

public interface PositionService {

	public Mono<List<String>> getAllPositions();

	public boolean addPosition(Position position);

	public boolean deletePosition(Position position);

	public Mono<Boolean> userAddPosition(Position position, String username);

	public Mono<Boolean> userDeletePosition(Position position, String username);

}
