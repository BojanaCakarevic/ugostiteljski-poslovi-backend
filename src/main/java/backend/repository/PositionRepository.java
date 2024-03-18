package backend.repository;
import backend.model.Pozicije;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PositionRepository extends JpaRepository<Pozicije, Integer> {

	List<Pozicije> findAll();
	Pozicije findByNaziv(String nazivPozicije);
	
	@Query("SELECT DISTINCT p.naziv FROM Pozicije p")
	List<String> findAllNaziv();
	
}
