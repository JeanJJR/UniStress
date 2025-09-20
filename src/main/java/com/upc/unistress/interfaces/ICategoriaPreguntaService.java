package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.CategoriaPreguntaDTO;
import java.util.List;

public interface ICategoriaPreguntaService {
    void insertar(CategoriaPreguntaDTO dto);
    List<CategoriaPreguntaDTO> listar();
    void eliminar(Long id);
    CategoriaPreguntaDTO listId(Long id);
}