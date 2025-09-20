package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.NivelPreguntaDTO;
import java.util.List;

public interface INivelPreguntaService {
    void insertar(NivelPreguntaDTO dto);
    List<NivelPreguntaDTO> listar();
    void eliminar(Long id);
    NivelPreguntaDTO listId(Long id);
}
