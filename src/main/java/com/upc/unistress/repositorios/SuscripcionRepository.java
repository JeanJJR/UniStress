package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Suscripcion;
import com.upc.unistress.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    List<Suscripcion> findByUsuario_Id(Long usuarioId);
    // Todas las suscripciones activas
    List<Suscripcion> findByEstado(String estado);

    // Suscripciones activas en el último mes
    @Query("SELECT s FROM Suscripcion s WHERE s.estado = 'ACTIVA' AND s.fechaInicio >= :fecha")
    List<Suscripcion> findSuscripcionesActivasUltimoMes(LocalDate fecha);

    // Usuarios con suscripción premium activa
    @Query("SELECT s.usuario FROM Suscripcion s WHERE s.tipo = 'PREMIUM' AND s.estado = 'ACTIVA'")
    List<Usuario> findUsuariosPremiumActivos();

}
