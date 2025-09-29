package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar usuario por correo (para login)
    Optional<Usuario> findByCorreo(String correo);

    // Verificar si un correo ya está registrado
    boolean existsByCorreo(String correo);

    // Listar usuarios por tipo de rol
    List<Usuario> findByRol_TipoRol(String tipoRol);

    // Listar usuarios con suscripción activa (JOIN en Service)
    @Query("SELECT DISTINCT s.usuario FROM Suscripcion s WHERE s.estado = 'ACTIVA'")
    List<Usuario> findUsuariosConSuscripcionActiva();

    @Query("SELECT u FROM Usuario u WHERE u.rol.tipoRol = 'PSICOLOGO'")
    List<Usuario> findPsicologosDisponibles();

}
