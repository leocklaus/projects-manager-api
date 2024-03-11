package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.UserOutputDTO;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
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
    public ResponseEntity<?> getUsersPaged(){

        //TO BE IMPLEMENTED

        return ResponseEntity.ok("Olá");
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
