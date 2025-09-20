package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.BancoPreguntaDTO;
import java.util.List;

public interface IBancoPreguntaService {
    void insertar(BancoPreguntaDTO dto);
    List<BancoPreguntaDTO> listar();
    void eliminar(Long id);
    BancoPreguntaDTO listId(Long id);
    List<BancoPreguntaDTO> listarPorCategoria(Long categoriaId);
    List<BancoPreguntaDTO> listarPorNivel(Long nivelId);
    List<BancoPreguntaDTO> listarPorPsicologo(Long psicologoId);
}