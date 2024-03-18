package backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.DTO.OglasDTO;
import backend.model.Oglasi;
import backend.model.Objekti;
import backend.model.Pozicije;
import backend.model.TipObjekta;
import backend.repository.ObjektiRepository;
import backend.repository.OglasiRepository;
import backend.repository.PositionRepository;
import backend.service.OglasService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OglasiServiceImpl implements OglasService {

	private OglasiRepository oglasiRepository;
	private ObjektiRepository objektiRepository;
	private ModelMapper modelMapper;
	private PositionRepository positionRepository;

	@Autowired
	public OglasiServiceImpl(OglasiRepository oglasiRepository, ModelMapper modelMapper,
			PositionRepository positionRepository, ObjektiRepository objektiRepository) {
		this.oglasiRepository = oglasiRepository;
		this.modelMapper = modelMapper;
		this.positionRepository = positionRepository;
		this.objektiRepository = objektiRepository;
	}

	private OglasDTO convertToDTO(Oglasi oglas) {
		return modelMapper.map(oglas, OglasDTO.class);
	}

	private Oglasi convertToEntity(OglasDTO oglasDTO) {
		return modelMapper.map(oglasDTO, Oglasi.class);
	}

	@Override
	public List<OglasDTO> getAllOglasi() {
		List<Oglasi> oglasi = oglasiRepository.findAll();
		return oglasi.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public OglasDTO getOglasById(int id) {
		Optional<Oglasi> oglasOptional = oglasiRepository.findById(id);
		return oglasOptional.map(this::convertToDTO).orElse(null);
	}

	@Override
	public void saveOglas(OglasDTO oglasDTO) {
		Oglasi oglas = convertToEntity(oglasDTO);

		String nazivPozicije = oglasDTO.getPozicija().getNaziv();
		Pozicije pozicija = positionRepository.findByNaziv(nazivPozicije);
		oglas.setPozicije(pozicija);

		String nazivObjekta = oglasDTO.getObjekat().getNaziv();
		Objekti objekat = objektiRepository.findByNaziv(nazivObjekta);
		oglas.setObjekti(objekat);

		if (objekat != null) {
			oglas.setObjekti(objekat);
			oglas.setTipObjekta(objekat.getTipObjekta());
		}

		oglasiRepository.save(oglas);
	}

	@Override
	public List<OglasDTO> filterOglasiByPozicija(String pozicija) {
		List<Oglasi> oglasi = oglasiRepository.findByPozicije_Naziv(pozicija);
		return oglasi.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<OglasDTO> filterOglasiByObjekatId(int idObjekta) {
		List<Oglasi> oglasi = oglasiRepository.findByObjekti_IdObjekta(idObjekta);
		return oglasi.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<OglasDTO> filterOglasiByDatum(LocalDate datum) {
		List<Oglasi> oglasi = oglasiRepository.findByDatumGreaterThanEqualOrderByDatum(datum);
		return oglasi.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteOglas(int oglasId) {
		Optional<Oglasi> oglasOptional = oglasiRepository.findById(oglasId);
		if (oglasOptional.isPresent()) {
			Oglasi oglas = oglasOptional.get();
			oglasiRepository.delete(oglas);
		} else {
			throw new EntityNotFoundException("Oglas sa ID-jem " + oglasId + " nije pronađen.");
		}

	}

	public List<Oglasi> findByObjekti(Objekti objekti) {
		return oglasiRepository.findByObjekti(objekti);
	}

	@Override
	public void deleteExpiredAdvertisements() {
		LocalDate currentDate = LocalDate.now();
		List<Oglasi> expiredAds = oglasiRepository.findByDatumLessThan(currentDate);
		oglasiRepository.deleteInBatch(expiredAds);
	}

	@Override
	public List<Oglasi> findOglasiByGrad(String grad) {
		return oglasiRepository.findByObjekti_Lokacije_Grad(grad);
	}

	@Override
	public List<OglasDTO> filterOglasi(String nazivObjekta, String grad, String pozicija, TipObjekta tipObjekta) {
		List<Oglasi> oglasi = oglasiRepository.findAll();

		if (nazivObjekta != null && !nazivObjekta.isEmpty()) {
			Objekti objekat = objektiRepository.findByNaziv(nazivObjekta);
			oglasi = oglasi.stream()
					.filter(oglas -> oglas.getObjekti().equals(objekat))
					.collect(Collectors.toList());
		}

		if (grad != null && !grad.isEmpty()) {
		    oglasi = findOglasiByGrad(grad);
		} else {
		    oglasi = oglasiRepository.findAll();
		}

		if (pozicija != null) {
		    oglasi = oglasi.stream()
		            .filter(oglas -> {
		                Pozicije oglasPozicija = oglas.getPozicije();
		                return oglasPozicija != null && oglasPozicija.getNaziv() != null && oglasPozicija.getNaziv().equals(pozicija);
		            })
		            .collect(Collectors.toList());
		}

		if (tipObjekta != null) {
		    oglasi = oglasi.stream()
		            .filter(oglas -> {
		                TipObjekta oglasTipObjekta = oglas.getTipObjekta();
		                return oglasTipObjekta != null && oglasTipObjekta.equals(tipObjekta);
		            })
		            .collect(Collectors.toList());
		}

		return oglasi.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

		
}
