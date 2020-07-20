package com.anjowe.behive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anjowe.behive.model.Position;
import com.anjowe.behive.service.PositionService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
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
	public boolean addPosition(@RequestParam("position") String position) {
		return positionService.addPosition(new Position(position));
	}

	@DeleteMapping("/position")
	public boolean getAllPositions(@RequestParam("position") String position) {
		return positionService.deletePosition(new Position(position));
	}

}
