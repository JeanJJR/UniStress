package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.PerfilDTO;

import java.util.List;
import java.util.Optional;

public interface IPerfilService {

    // Crear perfil
    void insert(PerfilDTO perfilDTO);

    // Listar todos los perfiles
    List<PerfilDTO> list();

    // Eliminar perfil
    void delete(Long id);

    // Buscar perfil por id
    PerfilDTO listId(Long id);

    // Buscar perfil por id de usuario
    Optional<PerfilDTO> findByUsuarioId(int usuarioId);

    // Listar perfiles por tipo (ESTUDIANTE, PSICOLOGO)
    List<PerfilDTO> listarPorTipoPerfil(String tipoPerfil);
}
