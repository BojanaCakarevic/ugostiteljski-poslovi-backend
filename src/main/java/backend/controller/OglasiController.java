package backend.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.DTO.OglasDTO;
import backend.model.Lokacije;
import backend.model.Oglasi;
import backend.model.Pozicije;
import backend.model.TipObjekta;
import backend.repository.OglasiRepository;
import backend.service.impl.LokacijeServiceImpl;
import backend.service.impl.ObjektiServiceImpl;
import backend.service.impl.OglasiServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/oglasi")
public class OglasiController {

	private OglasiServiceImpl oglasService;
	private OglasiRepository oglasRepo;
	private LokacijeServiceImpl locService;
	private ObjektiServiceImpl objService;
	
	@Autowired
	public OglasiController(OglasiServiceImpl oglasService, LokacijeServiceImpl locService, OglasiRepository oglasRepo, ObjektiServiceImpl objService) {
		super();
		this.oglasService = oglasService;
		this.oglasRepo = oglasRepo;
		this.locService = locService;
		this.objService = objService;
	}

	@GetMapping
    public List<OglasDTO> getAllOglasi() {
        return oglasService.getAllOglasi();
    }
	
	@GetMapping("/by-grad/{grad}")
	public ResponseEntity<List<Oglasi>> getOglasiByGrad(@PathVariable String grad) {
	    List<Oglasi> oglasi = oglasService.findOglasiByGrad(grad);
	    return new ResponseEntity<>(oglasi, HttpStatus.OK);
	}

    @GetMapping("/filter-by-pozicija/{pozicija}")
    public List<OglasDTO> filterOglasiByPozicija(@PathVariable String pozicija) {
        return oglasService.filterOglasiByPozicija(pozicija);
    }
    
    @GetMapping("/filter-by-objekat-id/{idObjekta}")
    public List<OglasDTO> filterOglasiByObjekat(@PathVariable int idObjekta) {
    	return oglasService.filterOglasiByObjekatId(idObjekta);
    }
    
    @GetMapping("/filter-by-objekat-tip/{tipObjekta}")
    public List<Oglasi> filterOglasiByTipObjekta(@PathVariable TipObjekta tipObjekta) {
    	return oglasRepo.findByObjekti_TipObjekta(tipObjekta);
    }
    
    @GetMapping("/filter-oglasi")
    public List<OglasDTO> filterOglasi(
            @RequestParam(required = false) String nazivObjekta,
            @RequestParam(required = false) String grad,
            @RequestParam(required = false) String pozicija,
            @RequestParam(required = false) TipObjekta tipObjekta) {
        return oglasService.filterOglasi(nazivObjekta, grad, pozicija, tipObjekta);
    }
    
    @GetMapping("/svi-gradovi")
    public List<String> sviGradovi() {
        return locService.getAllGradovi();
    }
    
    @GetMapping("/tipovi-objekta")
    public List<TipObjekta> findAllTipoviObjekata() {
        return Arrays.asList(TipObjekta.values());
    }
    
    @PostMapping
    public ResponseEntity<Void> saveOglas(@RequestBody OglasDTO oglasDTO) {
        oglasService.saveOglas(oglasDTO);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{oglasId}")
    public ResponseEntity<String> deleteOglas(@PathVariable int oglasId) {
        try {
            oglasService.deleteOglas(oglasId);
            return new ResponseEntity<>("Oglas uspešno obrisan.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Oglas nije pronađen.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške prilikom brisanja oglasa.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    
    
}