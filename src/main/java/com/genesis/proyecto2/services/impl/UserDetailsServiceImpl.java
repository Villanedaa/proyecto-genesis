package com.genesis.proyecto2.services.impl;

import com.genesis.proyecto2.exception.UserInactiveException;
import com.genesis.proyecto2.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

        var usuario = usuarioRepository.findByNombreUsuarioWithRoles(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombreUsuario));

        // Verificamos que la cuenta esté activa antes de permitir el login
        if (!"ACTIVO".equals(usuario.getEstado())) {
            throw new UserInactiveException(usuario.getNombreUsuario());
        }

        // Construimos el objeto User de Spring Security
        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasenia())
                .disabled(false) // Ya verificamos arriba que está ACTIVO
                .authorities(usuario.getUsuarioRoles().stream()
                        .map(ur -> ur.getRol().getNombre())
                        .toArray(String[]::new))
                .build();
    }
}