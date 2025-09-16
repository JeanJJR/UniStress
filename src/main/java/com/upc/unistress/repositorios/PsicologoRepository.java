package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
   public List<Psicologo> findAllByNombreContainingIgnoreCase(String palabra);
}
