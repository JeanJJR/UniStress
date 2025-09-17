package com.upc.unistress.interfaces;


import com.upc.unistress.dtos.RolDTO;

import java.util.List;

public interface IRolService {

    // Crear un rol
    void insert(RolDTO rolDTO);

    // Listar todos los roles
    List<RolDTO> list();

    // Eliminar rol
    void delete(Long id);

    // Buscar rol por id
    RolDTO listId(Long id);

    // Buscar rol por nombre
    RolDTO findByTipoRol(String tipoRol);
}