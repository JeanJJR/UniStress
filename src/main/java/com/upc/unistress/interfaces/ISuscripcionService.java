package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.SuscripcionDTO;
import com.upc.unistress.dtos.UsuarioDTO;

import java.time.LocalDate;
import java.util.List;

public interface ISuscripcionService {
    void insertar(SuscripcionDTO dto);
    List<SuscripcionDTO> listar();
    void eliminar(Long id);
    SuscripcionDTO listId(Long id);
    List<SuscripcionDTO> listarPorUsuario(Long usuarioId);
    List<SuscripcionDTO> listarPorEstado(String estado);
    List<SuscripcionDTO> listarActivasUltimoMes(LocalDate fecha);
    List<UsuarioDTO> listarUsuariosPremiumActivos();
}
