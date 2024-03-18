package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.model.Lokacije;

public interface LokacijeRepository extends JpaRepository<Lokacije, Integer> {

	@Query("SELECT DISTINCT l.grad FROM Lokacije l")
    List<String> findAllGradovi();
}
