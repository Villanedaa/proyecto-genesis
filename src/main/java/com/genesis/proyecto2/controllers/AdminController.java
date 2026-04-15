package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.MetricsResponse;
import com.genesis.proyecto2.dtos.PaginatedUsers;
import com.genesis.proyecto2.dtos.RechargeTokensRequest;
import com.genesis.proyecto2.dtos.UpdateStatusRequest;
import com.genesis.proyecto2.services.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IUsuarioService usuarioService;

    @GetMapping("/users")
    public ResponseEntity<PaginatedUsers> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(usuarioService.getAdminUsers(page, size));
    }

    @PatchMapping("/users/{userId}/status")
    public ResponseEntity<Void> updateUserStatus(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateStatusRequest request) {
        usuarioService.updateUserStatus(userId, request.getStatus());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/recharge")
    public ResponseEntity<Void> rechargeTokens(
            @PathVariable Long userId,
            @Valid @RequestBody RechargeTokensRequest request) {
        usuarioService.rechargeTokens(userId, request.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/metrics")
    public ResponseEntity<MetricsResponse> getMetrics() {
        return ResponseEntity.ok(usuarioService.getMetrics());
    }
}
