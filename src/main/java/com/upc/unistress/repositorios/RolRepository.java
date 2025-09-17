package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    // Buscar rol por nombre (ADMIN, ESTUDIANTE, PSICOLOGO)
    Optional<Rol> findByTipoRol(String tipoRol);
}
