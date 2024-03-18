package backend.service;

import backend.model.Korisnici;

public interface AuthenticationService {

	Korisnici signInAndReturnJWT(Korisnici signInRequest);
	
}
