package com.upc.unistress.repositorios;

import com.upc.unistress.dtos.SesionDTO;
import com.upc.unistress.entidades.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByPsicologo_Id(Long psicologoId);
    List<Sesion> findByEstudiante_Id(Long estudianteId);
    List<Sesion> findByFechaBetween(LocalDate fechaInicial, LocalDate fechaFinal);
}
