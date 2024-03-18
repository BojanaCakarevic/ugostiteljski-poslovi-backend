package backend.service;

import backend.model.*;

public interface JwtRefreshTokenService {

	JwtRefreshToken createRefreshToken(int userId);
	Korisnici generateAccessTokenFromRefreshToken(String refreshTokenId);
	
}
