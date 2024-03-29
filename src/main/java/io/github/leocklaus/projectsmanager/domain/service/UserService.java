package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.api.dto.UserEditDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserEditPasswordDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserOutputDTO;
import io.github.leocklaus.projectsmanager.domain.exception.UserNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.UserNotMatchingPasswordsException;
import io.github.leocklaus.projectsmanager.domain.model.Authorities;
import io.github.leocklaus.projectsmanager.domain.model.Role;
import io.github.leocklaus.projectsmanager.domain.model.User;
import io.github.leocklaus.projectsmanager.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    public Page<UserOutputDTO> getUsersPaged(Pageable pageable) {
        Page<User> users = this.userRepository.findAll(pageable);
        return users.map(UserOutputDTO::new);
    }

    public UserOutputDTO findUserById(String id){
        var user = getUserByIdOrThrowsExceptionIfNotExists(id);
        return new UserOutputDTO(user);
    }

    @Transactional
    public UserOutputDTO createUser(UserInputDTO dto) {
        var user = new User(dto);
        setHashPassword(user, dto.password());
        user = userRepository.save(user);

        return new UserOutputDTO(user);
    }

    @Transactional
    public UserOutputDTO updateUser(String userId, UserEditDTO dto) {

        var user = getUserByIdOrThrowsExceptionIfNotExists(userId);

        checkIfIdBelongsToLoggedUserOrThrowsNotAuthorizeException(userId);
        
        user = updateUserFromDTO(user, dto);

        return new UserOutputDTO(userRepository.save(user));

    }

    @Transactional
    public void deleteUser(String id) {
        var user = getUserByIdOrThrowsExceptionIfNotExists(id);
        checkIfIdBelongsToLoggedUserOrThrowsNotAuthorizeException(id);

        userRepository.delete(user);
    }

    @Transactional
    public void updateUserPassword(String id, UserEditPasswordDTO dto) {
        var user = getUserByIdOrThrowsExceptionIfNotExists(id);
        checkIfIdBelongsToLoggedUserOrThrowsNotAuthorizeException(id);

        if(!passwordEncoder.matches(dto.password(), user.getPassword())){
            throw new UserNotMatchingPasswordsException();
        }

        setHashPassword(user, dto.password());

        userRepository.save(user);
    }

    @Transactional
    public void setUserAsAdmin(String id){
        var user = getUserByIdOrThrowsExceptionIfNotExists(id);
        user.setAdmin();
        userRepository.save(user);
    }

    public User getUserByIdOrThrowsExceptionIfNotExists(String id){
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new UserNotFoundException(id));
        return user;
    }

    private void setHashPassword(User user, String password){
        password = passwordEncoder.encode(password);
        user.setPassword(password);
    }

    private boolean checkIfIdBelongsToLoggedUserOrThrowsNotAuthorizeException(String id){

        var loggedUser = getLoggedUser();

        return loggedUser.getId().toString().equals(id);
    }

    private User updateUserFromDTO(User user, UserEditDTO dto){
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());

        return user;
    }

    public User getLoggedUser(){
        return authService.getLoggedUser();
    }

}
