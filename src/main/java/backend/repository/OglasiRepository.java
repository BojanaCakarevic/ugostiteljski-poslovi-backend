package backend.repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.model.Objekti;
import backend.model.Oglasi;
import backend.model.TipObjekta;

public interface OglasiRepository extends JpaRepository<Oglasi, Integer> {

	List<Oglasi> findByPozicije_Naziv(String naziv);
	List<Oglasi> findByObjekti_IdObjekta(int idObjekta);
	List<Oglasi> findByDatumGreaterThanEqualOrderByDatum(LocalDate datum);
	List<Oglasi> findAll();
	List<Oglasi> findByDatumLessThan(LocalDate datum);
	List<Oglasi> findByObjekti(Objekti objekti);
	List<Oglasi> findByObjekti_TipObjekta(TipObjekta tipObjekta);

	@Query("SELECT o FROM Oglasi o JOIN o.objekti obj JOIN obj.lokacije lok WHERE lok.grad = :grad")
    List<Oglasi> findByObjekti_Lokacije_Grad(@Param("grad") String grad);
    

}
