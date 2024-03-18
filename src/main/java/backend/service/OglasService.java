package backend.service;

import java.time.LocalDate;
import java.util.List;

import backend.DTO.OglasDTO;
import backend.model.Objekti;
import backend.model.Oglasi;
import backend.model.TipObjekta;

public interface OglasService {

	List<OglasDTO> getAllOglasi();
    OglasDTO getOglasById(int id);
    void saveOglas(OglasDTO oglasDTO);
    List<OglasDTO> filterOglasiByPozicija(String nazivPozicije);
    List<OglasDTO> filterOglasiByObjekatId(int idObjekta);
    List<OglasDTO> filterOglasiByDatum(LocalDate datum);
    void deleteOglas(int oglasId);
    void deleteExpiredAdvertisements();
    List<Oglasi> findOglasiByGrad(String grad);
	List<OglasDTO> filterOglasi(String nazivObjekta, String grad, String pozicija, TipObjekta tipObjekta);
}
