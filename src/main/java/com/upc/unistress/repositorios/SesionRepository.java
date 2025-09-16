package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
}
