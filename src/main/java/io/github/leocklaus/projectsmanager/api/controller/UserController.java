package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.UserOutputDTO;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<?> createUser(){

        //TO BE IMPLEMENTED

        var uri = URI.create("/users/id");

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(){

        //TO BE IMPLEMENTED

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(){

        //TO BE IMPLEMENTED

        return ResponseEntity.noContent().build();
    }

}
