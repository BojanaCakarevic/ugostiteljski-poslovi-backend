package backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import backend.model.Korisnici;
import backend.security.JwtProvider;
import backend.security.UserPrincipal;
import backend.service.AuthenticationService;
import backend.service.JwtRefreshTokenService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private JwtRefreshTokenService jwtRefService;
	
	@Override
	public Korisnici signInAndReturnJWT(Korisnici signInRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getLozinka()));
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String jwt = jwtProvider.generateToken(userPrincipal);
		
		Korisnici signInUser = userPrincipal.getUser();
		signInUser.setAccessToken(jwt);
		signInUser.setRefreshToken(jwtRefService.createRefreshToken(signInUser.getIdKorisnika()).getTokenId());

		return signInUser;
	}

}
