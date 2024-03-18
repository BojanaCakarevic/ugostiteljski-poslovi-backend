package backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.*;

public interface OceneRepository extends JpaRepository<Ocene, Integer> {

	List<Ocene> findByObjekti_IdObjekta(int idObjekta);
	
}
