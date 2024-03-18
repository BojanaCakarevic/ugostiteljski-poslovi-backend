package backend.service.impl;

import backend.repository.LokacijeRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import backend.model.*;

@Service
public class LokacijeServiceImpl {

	private LokacijeRepository locRepo;

	public LokacijeServiceImpl(LokacijeRepository locRepo) {
		super();
		this.locRepo = locRepo;
	}
	
	  public List<String> getAllGradovi() {
	        List<Lokacije> sveLokacije = locRepo.findAll();
	        List<String> gradovi = sveLokacije
	        		.stream()
	        		.map(Lokacije::getGrad)
	        		.map(String::toLowerCase)
	        		.distinct()
	        		.collect(Collectors.toList());
	        return gradovi;
	    }
	
}
