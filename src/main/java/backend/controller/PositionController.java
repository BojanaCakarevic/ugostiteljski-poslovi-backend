package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Pozicije;
import backend.service.impl.PositionServiceImpl;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

	private PositionServiceImpl positionService;

	@Autowired
	public PositionController(PositionServiceImpl positionService) {
		this.positionService = positionService;
	}

	@GetMapping
	public List<Pozicije> gdetAllPositions() {
		return positionService.getAllPositions();
	}
	
	@GetMapping("/positions-name")
	public List<String> getAllPositionsByName() {
		return positionService.getAllPositionsByName();
}
	
}
