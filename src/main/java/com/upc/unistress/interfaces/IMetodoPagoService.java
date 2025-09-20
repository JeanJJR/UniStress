package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.MetodoPagoDTO;

import java.util.List;

public interface IMetodoPagoService {
    void insertar(MetodoPagoDTO dto);
    List<MetodoPagoDTO> listar();
    void eliminar(Long id);
    MetodoPagoDTO listId(Long id);
}