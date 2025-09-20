package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.PreguntaTestDTO;
import java.util.List;

public interface IPreguntaTestService {
    void insertar(PreguntaTestDTO dto);
    List<PreguntaTestDTO> listar();
    void eliminar(Long id);
    PreguntaTestDTO listId(Long id);
    List<PreguntaTestDTO> listarPorTest(Long testId);
}
