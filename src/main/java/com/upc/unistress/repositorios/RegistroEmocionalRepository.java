package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.RegistroEmocional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroEmocionalRepository extends JpaRepository<RegistroEmocional, Long> {
    List<RegistroEmocional> findByUsuario_Id(Long usuarioId);
    List<RegistroEmocional> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);
}