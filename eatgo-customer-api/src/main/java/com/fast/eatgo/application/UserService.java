package com.fast.eatgo.application;

import com.fast.eatgo.domain.EmailNotExistedException;
import com.fast.eatgo.domain.User;
import com.fast.eatgo.domain.UserExistedException;
import com.fast.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(User user) {
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());
        if (opUser.isPresent()) {
            throw new UserExistedException(user.getEmail());
        }

        user
                .setPassword(passwordEncoder(user.getPassword()))
                .setLevel(1L);

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistedException(email));

        passwordEncoder().matches()

        return user;
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
