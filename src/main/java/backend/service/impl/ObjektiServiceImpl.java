package backend.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.DTO.ObjekatDTO;
import backend.DTO.OglasDTO;
import backend.model.Lokacije;
import backend.model.Objekti;
import backend.model.Oglasi;
import backend.model.TipObjekta;
import backend.repository.LokacijeRepository;
import backend.repository.ObjektiRepository;
import backend.service.ObjektiService;

@Service
public class ObjektiServiceImpl implements ObjektiService {

	private ObjektiRepository objektiRepository;
	private ModelMapper modelMapper;
	private LokacijeRepository lokacijeRepository;
	
	@Autowired
	public ObjektiServiceImpl(ObjektiRepository objektiRepository, ModelMapper modelMapper, LokacijeRepository lokacijeRepository) {
		this.objektiRepository = objektiRepository;
		this.modelMapper = modelMapper;
		this.lokacijeRepository = lokacijeRepository;
	}
	
    private ObjekatDTO convertToDTO(Objekti objekti) {
        return modelMapper.map(objekti, ObjekatDTO.class);
    }

    private Objekti convertToEntity(ObjekatDTO objektiDTO) {
        return modelMapper.map(objektiDTO, Objekti.class);
    }
	
	@Override
	public void saveObjekat(ObjekatDTO saveObjekatDTO){
		
		Objekti objekat = convertToEntity(saveObjekatDTO);
	    List<Lokacije> lokacije = saveObjekatDTO.getLokacije();
	    TipObjekta tipObjekta = saveObjekatDTO.getTipObjekta();

	    objekat.setTipObjekta(tipObjekta);

	    if (objekat.getLokacije() != null) {
	        objekat.getLokacije().clear();
	    }

	    for (Lokacije lokacija : lokacije) {
	        objekat.addLokacija(lokacija);
	        lokacija.setObjekti(objekat);
	    }

	    objektiRepository.save(objekat);
	    	System.out.println("Objekat: " + objekat);
	        System.out.println("Lokacije: " + lokacije);
	        System.out.println("Tip objekta: " + tipObjekta);

	}
	
	public void deleteObjekatWithLokacije(int idObjekta) {
	    Objekti objekat = objektiRepository.findById(idObjekta).orElse(null);
	    if (objekat != null) {
	        List<Lokacije> lokacije = objekat.getLokacije();
	        objekat.setLokacije(null);
	        objektiRepository.save(objekat); 
	        
	        if (lokacije != null) {
	            lokacijeRepository.deleteAll(lokacije);
	        }

	        objektiRepository.deleteById(idObjekta);
	    }
	}
	
	public List<Objekti> pretraziObjektePoLokaciji(String lokacija) {
	    return objektiRepository.findByLokacijeGrad(lokacija);
	}

	@Override
	public List<Objekti> pretraziPoTipu(TipObjekta tip) {
		return objektiRepository.findByTipObjekta(tip);
	}
	
	public List<TipObjekta> findAllTipoviObjekata() {
		return objektiRepository.findAllTipoviObjekata();
	}

}
