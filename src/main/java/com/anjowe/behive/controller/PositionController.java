package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Position;
import com.anjowe.behive.service.PositionService;

import reactor.core.publisher.Mono;

@RestController
public class PositionController {

	private PositionService positionService;

	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	@GetMapping("/position")
	public Mono<List<String>> getAllPositions() {
		return positionService.getAllPositions();
	}

	@PostMapping("/position")
	public boolean adminAddPosition(@RequestBody List<String> positions) {
		for(String positionName: positions) {
			positionService.addPosition(new Position(positionName));
		}
		return true;
	}

	@DeleteMapping("/position")
	public boolean adminDeletePosition(@RequestBody List<String> positions) {
		for(String positionName: positions) {
			positionService.deletePosition(new Position(positionName));
		}
		return true;
	}

}