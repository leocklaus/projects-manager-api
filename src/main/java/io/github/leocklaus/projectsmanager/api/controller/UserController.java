package io.github.leocklaus.projectsmanager.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public ResponseEntity<?> getUsersPaged(){

        //TO BE IMPLEMENTED

        return ResponseEntity.ok("Olá");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){

        //TO BE IMPLEMENTED

        return ResponseEntity.ok().build();
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
