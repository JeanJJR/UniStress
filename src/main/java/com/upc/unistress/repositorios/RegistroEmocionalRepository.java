package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.RegistroEmocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroEmocionalRepository extends JpaRepository<RegistroEmocional, Long> {
    List<RegistroEmocional> findByUsuario_Id(Long usuarioId);
    List<RegistroEmocional> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);
    // Promedio de nivel emocional por emoción
    @Query("SELECT r.emocion, AVG(r.nivel) FROM RegistroEmocional r " +
            "WHERE r.usuario.id = :usuarioId GROUP BY r.emocion")
    List<Object[]> promedioNivelPorEmocion(@Param("usuarioId") Long usuarioId);

    // Evolución de emociones en el tiempo
    @Query("SELECT r.fechaRegistro, r.emocion, r.nivel FROM RegistroEmocional r " +
            "WHERE r.usuario.id = :usuarioId ORDER BY r.fechaRegistro ASC")
    List<Object[]> evolucionEmociones(@Param("usuarioId") Long usuarioId);

    @Query("SELECT r.fechaRegistro, r.emocion, r.nivel FROM RegistroEmocional r " +
            "WHERE r.usuario.id = :usuarioId AND r.fechaRegistro BETWEEN :fechaInicio AND :fechaFin " +
            "ORDER BY r.fechaRegistro ASC")
    List<Object[]> evolucionEmocionesEntreFechas(@Param("usuarioId") Long usuarioId,
                                                 @Param("fechaInicio") java.time.LocalDateTime fechaInicio,
                                                 @Param("fechaFin") java.time.LocalDateTime fechaFin);
}