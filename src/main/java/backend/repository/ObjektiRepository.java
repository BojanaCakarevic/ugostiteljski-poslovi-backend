package backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.model.Objekti;
import backend.model.TipObjekta;

public interface ObjektiRepository extends JpaRepository<Objekti, Integer> {

	List<Objekti> findAll();
	Objekti findByNaziv(String naziv);
	Objekti findByIdObjekta(int idObjekta);
	List<Objekti> findByTipObjekta(TipObjekta tip);
	
	@Query("SELECT o FROM Objekti o JOIN o.lokacije l WHERE l.grad = :lokacija")
	List<Objekti> findByLokacijeGrad(@Param("lokacija") String lokacija);
	 
	@Query("SELECT DISTINCT o.tipObjekta FROM Objekti o")
	List<TipObjekta> findAllTipoviObjekata();
	
}
