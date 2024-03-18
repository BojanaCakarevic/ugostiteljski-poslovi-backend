package backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import backend.model.Korisnici;
import backend.model.TipKorisnika;

public interface UserService {
	
	Korisnici saveUser(Korisnici user);
	Optional<Korisnici> findByUsername(String username);
	List<Korisnici> findAllUsers();
	void changeRole(TipKorisnika newRole, String username);
	
}
