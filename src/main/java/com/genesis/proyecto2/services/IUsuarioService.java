package com.genesis.proyecto2.services;

import com.genesis.proyecto2.dtos.RegistroUsuario;
import com.genesis.proyecto2.dtos.UsuarioResponse;
import com.genesis.proyecto2.dtos.UsuarioRolCrearRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * Definición de servicios para la gestión de cuentas de usuario y seguridad.
 */
public interface IUsuarioService {

    List<UsuarioResponse> findAll();

    Optional<UsuarioResponse> findById(Long id);

    Optional<UsuarioResponse> findByNombreUsuario(String nombreUsuario);

    // Crea el usuario a partir del DTO de registro y retorna el perfil seguro
    UsuarioResponse save(RegistroUsuario usuarioDto);

    // Al no tener UsuarioRolResponse, retornamos el perfil actualizado del usuario
    UsuarioResponse asignarRol(UsuarioRolCrearRequest request);

    Optional<UsuarioResponse> update(Long id, RegistroUsuario usuarioDto);

    boolean delete(Long id);
}