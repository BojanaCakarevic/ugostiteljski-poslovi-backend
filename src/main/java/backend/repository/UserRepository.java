package backend.repository;
import backend.model.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Korisnici, Integer> {

	Optional<Korisnici> findByUsername(String username);
	Boolean existsByUsername(String username);
	
	@Modifying
    @Query("update Korisnici k set k.tip = :role where k.username = :username")
    void updateUserRole(@Param("username") String username, @Param("role")TipKorisnika role);
}
