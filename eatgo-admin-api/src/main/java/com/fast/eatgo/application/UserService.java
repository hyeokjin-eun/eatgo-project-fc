package com.fast.eatgo.application;

import com.fast.eatgo.domain.User;
import com.fast.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(searchUser -> searchUser
                        .setEmail(user.getEmail())
                        .setName(user.getName())
                        .setLevel(user.getLevel()))
                .orElse(null);
    }

    @Transactional
    public User deactivateUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.deActive();
                    return user;
                })
                .orElse(null);
    }
}
