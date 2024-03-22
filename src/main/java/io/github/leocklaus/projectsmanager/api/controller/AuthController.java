package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.LoginInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.TokenOutputDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.UserOutputDTO;
import io.github.leocklaus.projectsmanager.domain.service.AuthService;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenOutputDTO> login(@RequestBody LoginInputDTO loginDTO){
        var token = authService.login(loginDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserOutputDTO> createUser(@RequestBody @Valid UserInputDTO dto){

        var user = userService.createUser(dto);

        var uri = URI.create("/users/" + user.UUID());

        return ResponseEntity.created(uri).body(user);
    }

}
