package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.SesionDTO;

import java.util.List;

public interface ISesionService {
    SesionDTO crearSesion(SesionDTO sesionDTO);
    List<SesionDTO> listarSesion();
    SesionDTO buscarSesionporId(Long id);
}
