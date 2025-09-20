package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.CategoriaPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaPreguntaRepository extends JpaRepository<CategoriaPregunta, Long> {
    Optional<CategoriaPregunta> findByNombre(String nombre);
}
