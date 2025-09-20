package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.NivelPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NivelPreguntaRepository extends JpaRepository<NivelPregunta, Long> {
    Optional<NivelPregunta> findByNombre(String nombre);
}
