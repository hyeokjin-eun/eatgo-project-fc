package com.fast.eatgo.application;

import com.fast.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());
        if (opUser.isPresent()) {
            throw new UserExistedException(user.getEmail());
        }

        user
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setLevel(1L);

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistedException(email));

        if (passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }

        return user;
    }
}
