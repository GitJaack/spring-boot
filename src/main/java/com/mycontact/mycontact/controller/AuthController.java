package com.mycontact.mycontact.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.mycontact.mycontact.model.UserModel;
import com.mycontact.mycontact.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentification")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Créer un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Email déjà utilisé", content = @Content())
    })
    public ResponseEntity<?> register(UserModel request) {
        try {
            UserModel savedUser = userService.register(request);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion à un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect", content = @Content())
    })
    public ResponseEntity<?> login(UserModel request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Connecté en tant que : " + authentication.getName());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
        }
    }
}