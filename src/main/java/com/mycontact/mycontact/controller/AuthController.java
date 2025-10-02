package com.mycontact.mycontact.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.mycontact.mycontact.model.DTO.LoginDTO;
import com.mycontact.mycontact.model.DTO.RegisterDTO;
import com.mycontact.mycontact.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Authentification")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Créer un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès", content = @Content()),
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto) {
        userService.register(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion à un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès", content = @Content()),
    })
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        String username = userService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.status(200).body("Connecté en tant que : " + username);
    }

}
