package hockey.kpi.backend.service;

import hockey.kpi.backend.model.User;
import hockey.kpi.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Користувач з логіном '" + user.getUsername() + "' вже зареєструвався");
        }

        String defaultPassword = user.getPasswordHash();

        String encryptedPassword = passwordEncoder.encode(defaultPassword);

        user.setPasswordHash(encryptedPassword);

        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}