package backend.service;
import java.util.List;

import backend.DTO.PozicijaDTO;
import backend.model.Pozicije;

public interface PositionService {

	public List<Pozicije> getAllPositions();
	public List<String> getAllPositionsByName();
	
}
