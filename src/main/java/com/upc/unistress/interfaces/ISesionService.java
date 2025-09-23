package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.SesionDTO;

import java.time.LocalDate;
import java.util.List;

public interface ISesionService {

    // Crear nueva sesión (incluye crear notificación para el psicólogo)
    void crearSesion(SesionDTO dto);

    // Listar todas las sesiones
    List<SesionDTO> listar();

    // Eliminar sesión
    void eliminar(Long id);

    // Listar sesiones entre fechas
    List<SesionDTO> listarPorFechas(LocalDate fechaInicial, LocalDate fechaFinal);
}
