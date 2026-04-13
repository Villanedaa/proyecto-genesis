package com.genesis.proyecto2.services.impl;

import com.genesis.proyecto2.dtos.RegistroUsuario;
import com.genesis.proyecto2.dtos.UsuarioResponse;
import com.genesis.proyecto2.dtos.UsuarioRolCrearRequest;
import com.genesis.proyecto2.entities.Usuario;
import com.genesis.proyecto2.exception.DuplicateResourceException;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.IUsuarioRepository;
import com.genesis.proyecto2.services.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Inyección para evitar texto plano

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioResponse> findById(Long id) {
        return Optional.of(
                usuarioRepository.findById(id)
                        .map(this::toResponse)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario", id))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioResponse> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).map(this::toResponse);
    }

    @Override
    @Transactional
    public UsuarioResponse save(RegistroUsuario dto) {
        // Verificamos unicidad antes de persistir
        if (usuarioRepository.findByNombreUsuario(dto.getNombreUsuario()).isPresent()) {
            throw new DuplicateResourceException("nombreUsuario", dto.getNombreUsuario());
        }
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new DuplicateResourceException("correo", dto.getCorreo());
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreo(dto.getCorreo());

        // Encriptar antes de guardar
        usuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));

        usuario.setSaldoTokens(100);
        usuario.setEstado("ACTIVO");

        // No seteamos fechaCreacion porque JPA tiene insertable = false
        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponse asignarRol(UsuarioRolCrearRequest request) {
        // Lanzamos ResourceNotFoundException si el usuario no existe
        return findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", request.getUsuarioId()));
    }

    @Override
    @Transactional
    public Optional<UsuarioResponse> update(Long id, RegistroUsuario dto) {
        return usuarioRepository.findById(id).map(existing -> {
            existing.setNombreUsuario(dto.getNombreUsuario());
            existing.setCorreo(dto.getCorreo());

            // Si la contraseña viene en el DTO, se encripta
            if (dto.getContrasenia() != null && !dto.getContrasenia().isEmpty()) {
                existing.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
            }

            return toResponse(usuarioRepository.save(existing));
        });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!usuarioRepository.existsById(id)) return false;
        usuarioRepository.deleteById(id);
        return true;
    }

    private UsuarioResponse toResponse(Usuario u) {
        UsuarioResponse res = new UsuarioResponse();
        res.setId(u.getId());
        res.setNombreUsuario(u.getNombreUsuario());
        res.setCorreo(u.getCorreo());
        res.setSaldoTokens(u.getSaldoTokens());
        res.setEstado(u.getEstado());
        res.setFechaCreacion(u.getFechaCreacion());
        return res;
    }
}