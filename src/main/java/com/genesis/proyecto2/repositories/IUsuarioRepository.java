package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 13/04/2026
 * */
import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByCorreo(String correo);

    /** Consulta optimizada para cargar roles y evitar el error LazyInitializationException por nombreUsuario
     *
     * @param nombreUsuario
     * @return
     */
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.usuarioRoles ur LEFT JOIN FETCH ur.rol WHERE u.nombreUsuario = :nombreUsuario")
    Optional<Usuario> findByNombreUsuarioWithRoles(@Param("nombreUsuario") String nombreUsuario);

    /** Consulta optimizada para cargar roles y evitar el error LazyInitializationException por correo
     *
     * @param correo
     * @return
     */
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.usuarioRoles ur LEFT JOIN FETCH ur.rol WHERE u.correo = :correo")
    Optional<Usuario> findByCorreoWithRoles(@Param("correo") String correo);
}
