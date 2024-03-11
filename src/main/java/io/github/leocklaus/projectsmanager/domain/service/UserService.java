package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
