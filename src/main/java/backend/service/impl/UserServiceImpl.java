package backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.model.Korisnici;
import backend.model.TipKorisnika;
import backend.repository.UserRepository;
import backend.service.UserService;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Korisnici saveUser(Korisnici user) {
        user.setLozinka(passwordEncoder.encode(user.getLozinka()));
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public Optional<Korisnici> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void changeRole(TipKorisnika newRole, String username) {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public List<Korisnici> findAllUsers() {
        return userRepository.findAll();
    }

}
