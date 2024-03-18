package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.DTO.ObjekatDTO;
import backend.model.Lokacije;
import backend.model.Objekti;
import backend.model.Oglasi;
import backend.model.TipObjekta;
import backend.repository.ObjektiRepository;
import backend.service.impl.ObjektiServiceImpl;

@RestController
@RequestMapping("/api/objekti")
public class ObjektiController {

	private ObjektiRepository objektiRepository;
	private ObjektiServiceImpl objektiService;
	
	public ObjektiController(ObjektiRepository objektiRepository, ObjektiServiceImpl objektiService) {
		super();
		this.objektiRepository = objektiRepository;
		this.objektiService = objektiService;
	}

	@GetMapping
	public List<Objekti> getAll() {
		return objektiRepository.findAll();
	}
	
	@GetMapping("/pretrazi/{lokacija}")
	public List<Objekti> pretraziObjektePoLokaciji(@PathVariable String lokacija) {
	    return objektiService.pretraziObjektePoLokaciji(lokacija);
	}
	
	@PostMapping
	public void saveObjekat(@RequestBody ObjekatDTO saveObjekatDTO) {
	    objektiService.saveObjekat(saveObjekatDTO);
	}

	@GetMapping("/pretraga-po-tipu/{tip}")
	public List<Objekti> findByType(@PathVariable TipObjekta tip) {
		return objektiService.pretraziPoTipu(tip);
	}

	@GetMapping("/{naziv}")
	public Objekti getObjekatByNaziv(@PathVariable String naziv) {
		return objektiRepository.findByNaziv(naziv);
	}
	
}
