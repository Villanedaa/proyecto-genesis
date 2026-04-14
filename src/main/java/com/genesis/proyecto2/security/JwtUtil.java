package com.genesis.proyecto2.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secreto;

    @Value("${jwt.expiration}")
    private long expiracion;

    private SecretKey obtenerClaveFirma() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secreto));
    }

    public String generarToken(UserDetails detallesUsuario) {
        List<String> roles = detallesUsuario.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claimsExtra = new HashMap<>();
        claimsExtra.put("roles", roles);
        // !!! se podria incluir el id de usuario para montos de tokens...

        return Jwts.builder()
                .claims(claimsExtra)
                .subject(detallesUsuario.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(obtenerClaveFirma())
                .compact();
    }

    public String extraerNombreUsuario(String token) {
        return extraerClaims(token).getSubject();
    }

    public boolean esTokenValido(String token, UserDetails detallesUsuario) {
        String nombreUsuario = extraerNombreUsuario(token);
        return nombreUsuario.equals(detallesUsuario.getUsername()) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        return extraerClaims(token).getExpiration().before(new Date());
    }

    private Claims extraerClaims(String token) {
        return Jwts.parser()
                .verifyWith(obtenerClaveFirma())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}