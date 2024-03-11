package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.UserEditDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserEditPasswordDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserOutputDTO;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserOutputDTO>> getUsersPaged(Pageable pageable){

        Page<UserOutputDTO> users = userService.getUsersPaged(pageable);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable String id){

        var user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> createUser(@RequestBody @Valid UserInputDTO dto){

        var user = userService.createUser(dto);

        var uri = URI.create("/users/" + user.UUID());

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable String id, @RequestBody UserEditDTO dto){

        UserOutputDTO user = userService.updateUser(id, dto);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/updatepassword")
    public ResponseEntity<?> updatePassword(@PathVariable String id, @RequestBody UserEditPasswordDTO dto){
        userService.updateUserPassword(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
