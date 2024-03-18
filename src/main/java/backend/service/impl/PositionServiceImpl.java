package backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.DTO.PozicijaDTO;
import backend.model.Pozicije;
import backend.repository.PositionRepository;
import backend.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Pozicije> getAllPositions() {
        return positionRepository.findAll();
    }

	@Override
	public List<String> getAllPositionsByName() {
		return positionRepository.findAllNaziv();
	}
	
}
