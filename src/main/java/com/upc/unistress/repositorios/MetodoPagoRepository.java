package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
}