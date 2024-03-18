package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Utisci;

public interface UtisciRepository extends JpaRepository<Utisci, Integer> {

	List<Utisci> findByObjekti_IdObjekta(int idObjekta);
	
}
