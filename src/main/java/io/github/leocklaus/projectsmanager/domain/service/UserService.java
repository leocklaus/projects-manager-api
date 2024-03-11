package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.api.dto.UserOutputDTO;
import io.github.leocklaus.projectsmanager.domain.exception.UserNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.User;
import io.github.leocklaus.projectsmanager.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserOutputDTO> getUsersPaged(Pageable pageable) {
        Page<User> users = this.userRepository.findAll(pageable);
        return users.map(UserOutputDTO::new);
    }

    public UserOutputDTO findUserById(String id){
        var user = getUserByIdOrThrowsExceptionIfNotExists(id);
        return new UserOutputDTO(user);
    }

    private User getUserByIdOrThrowsExceptionIfNotExists(String id){
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new UserNotFoundException(id));
        return user;
    }

}
