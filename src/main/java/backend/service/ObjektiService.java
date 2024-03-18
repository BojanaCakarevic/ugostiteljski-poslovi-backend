package backend.service;

import java.util.List;

import backend.DTO.ObjekatDTO;
import backend.model.Lokacije;
import backend.model.Objekti;
import backend.model.TipObjekta;

public interface ObjektiService {

	void saveObjekat(ObjekatDTO saveObjekatDTO);
	List<Objekti> pretraziObjektePoLokaciji(String lokacija);
	List<Objekti> pretraziPoTipu(TipObjekta tip);
}
