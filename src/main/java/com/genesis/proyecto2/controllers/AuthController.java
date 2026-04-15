package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.AuthResponse;
import com.genesis.proyecto2.dtos.LoginRequest;
import com.genesis.proyecto2.dtos.RegistroUsuario;
import com.genesis.proyecto2.security.JwtUtil;
import com.genesis.proyecto2.services.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * Endpoint público de autenticación y registro.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IUsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegistroUsuario request) {

        usuarioService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        /**
         * Autenticar: Spring Security validará contra UserDetailsService
        */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getContrasenia())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtil.generarToken(user);

        // Retornamos la clase local AuthResponse
        AuthResponse response = new AuthResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }
}
