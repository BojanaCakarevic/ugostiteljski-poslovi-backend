package backend.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Objekti;
import backend.model.Ocene;
import backend.repository.ObjektiRepository;
import backend.repository.OceneRepository;
import backend.service.OceneService;

@Service
public class OceneServiceImpl implements OceneService {

	@Autowired
	private OceneRepository oceneRepository;

	@Autowired
	private ObjektiRepository objektiRepository;

	public OceneServiceImpl(OceneRepository oceneRepository, ObjektiRepository objektiRepository) {
		super();
		this.oceneRepository = oceneRepository;
		this.objektiRepository = objektiRepository;
	}

	public Ocene dodajOcenu(int idObjekta, int ocenaAtmosfera, int ocenaBenefit, int ocenaKolektiv, int ocenaNapredak,
			int ocenaPlata, int ocenaUsloviRada, int ocenaVlasnik) {

		Objekti objekat = objektiRepository.findByIdObjekta(idObjekta);

		if (objekat != null) {
			Ocene novaOcena = new Ocene();
			novaOcena.setOcenaAtmosfera(ocenaAtmosfera);
			novaOcena.setOcenaBenefit(ocenaBenefit);
			novaOcena.setOcenaKolektiv(ocenaKolektiv);
			novaOcena.setOcenaNapredak(ocenaNapredak);
			novaOcena.setOcenaPlata(ocenaPlata);
			novaOcena.setOcenaUsloviRada(ocenaUsloviRada);
			novaOcena.setOcenaVlasnik(ocenaVlasnik);
			novaOcena.setObjekti(objekat);

			return oceneRepository.save(novaOcena);
		}

		return null;
	}

    public Double prosecnaOcenaObjekta(int idObjekta) {
        List<Ocene> ocene = oceneRepository.findByObjekti_IdObjekta(idObjekta);

        if (ocene != null && !ocene.isEmpty()) {
            double prosecnaOcena = ocene.stream()
                    .mapToDouble(o -> (o.getOcenaAtmosfera() + o.getOcenaBenefit() + o.getOcenaKolektiv() +
                                      o.getOcenaNapredak() + o.getOcenaPlata() + o.getOcenaUsloviRada() +
                                      o.getOcenaVlasnik()) / 7.0)
                    .average()
                    .orElse(0.0);

            return Math.round(prosecnaOcena * 10.0) / 10.0;  // Zaokruživanje na jednu decimalu
        }

        return null;
    }
    
    public double calculateAverageVlasnikRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaVlasnik");
    }
    
    public double calculateAverageUsloviRadaRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaUsloviRada");
    }
    
    public double calculateAveragePlataRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaPlata");
    }
    
    public double calculateAverageNapredakRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaNapredak");
    }
    
    public double calculateAverageAtmosferaRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaAtmosfera");
    }

    public double calculateAverageBenefitRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaBenefit");
    }

    public double calculateAverageKolektivRatingForObjekat(int idObjekta) {
        return calculateAverageRatingForCategory(idObjekta, "ocenaKolektiv");
    }
    
    private double calculateAverageRatingForCategory(int idObjekta, String categoryField) {
        List<Ocene> ocene = oceneRepository.findByObjekti_IdObjekta(idObjekta);

        if (ocene.isEmpty()) {
            return -1;
        }

        double avgRating = ocene.stream()
                .mapToDouble(ocena -> {
                    try {
                        Field field = Ocene.class.getDeclaredField(categoryField);
                        field.setAccessible(true);
                        return field.getInt(ocena);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return 0;
                    }
                })
                .average()
                .orElse(0.0);

        return avgRating;
    }
    
}
