package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.PaginatedTransactions;
import com.genesis.proyecto2.dtos.SubscribeRequest;
import com.genesis.proyecto2.dtos.UserProfileResponse;
import com.genesis.proyecto2.services.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint para las acciones propias del usuario autenticado
 */
@RestController
@RequestMapping("/users/me")
@RequiredArgsConstructor
public class UserProfileController {

    private final IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UserProfileResponse> getCurrentProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(usuarioService.getProfile(email));
    }

    @GetMapping("/transactions")
    public ResponseEntity<PaginatedTransactions> getMyTransactions(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        String email = authentication.getName();
        return ResponseEntity.ok(usuarioService.getTransactions(email, page, size));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribeToPlan(
            Authentication authentication,
            @Valid @RequestBody SubscribeRequest request) {
        String email = authentication.getName();
        usuarioService.subscribeToPlan(email, request);
        return ResponseEntity.ok().build();
    }
}
