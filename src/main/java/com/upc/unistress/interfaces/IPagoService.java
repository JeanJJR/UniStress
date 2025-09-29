package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.PagoDTO;

import java.time.LocalDate;
import java.util.List;

public interface IPagoService {
    void insertar(PagoDTO dto);
    List<PagoDTO> listar();
    void eliminar(Long id);
    PagoDTO listId(Long id);
    List<PagoDTO> listarPorSuscripcion(Long suscripcionId);
    List<PagoDTO> listarPorUsuario(Long usuarioId);
    List<PagoDTO> listarPagosUltimoMes(LocalDate fecha);
    Double obtenerTotalRecaudadoUltimoMes(LocalDate fecha);
}
