package backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import backend.model.JwtRefreshToken;
import backend.model.Korisnici;
import backend.repository.JwtRefreshTokenRepository;
import backend.repository.UserRepository;
import backend.security.JwtProvider;
import backend.security.UserPrincipal;
import backend.service.JwtRefreshTokenService;
import backend.utils.SecurityUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {

    @Value("${app.jwt.refresh-expiration-in-ms}")
    private Long REFRESH_EXPIRATION_IN_MS;

    @Autowired
    private JwtRefreshTokenRepository jwtRefreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public JwtRefreshToken createRefreshToken(int userId) {
    	if (REFRESH_EXPIRATION_IN_MS <= 0) {
            throw new IllegalArgumentException("REFRESH_EXPIRATION_IN_MS must be a positive value.");
        }
    	
        JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();

        jwtRefreshToken.setTokenId(UUID.randomUUID().toString());
        jwtRefreshToken.setUserId(userId);
        jwtRefreshToken.setCreateDate(LocalDateTime.now());
        jwtRefreshToken.setExpirationDate(LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));

        return jwtRefreshTokenRepository.save(jwtRefreshToken);
    }

    @Override
    public Korisnici generateAccessTokenFromRefreshToken(String refreshTokenId) {
        JwtRefreshToken jwtRefreshToken = jwtRefreshTokenRepository.findById(refreshTokenId).orElseThrow();

        if (jwtRefreshToken.getExpirationDate().isBefore(Instant.now().atZone(ZoneOffset.UTC).toLocalDateTime())) {
            throw new RuntimeException("JWT refresh token is not valid.");
        }


        Korisnici user = userRepository.findById(jwtRefreshToken.getUserId()).orElseThrow();

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(user.getIdKorisnika())
                .username(user.getUsername())
                .password(user.getLozinka())
                .authorities(Set.of(SecurityUtils.convertToAuthority(user.getTip().name())))
                .build();

        String accessToken = jwtProvider.generateToken(userPrincipal);

        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshTokenId);

        return user;
    }
}
