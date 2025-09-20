package com.upc.unistress.servicios;

import com.upc.unistress.dtos.RolDTO;
import com.upc.unistress.entidades.Rol;
import com.upc.unistress.interfaces.IRolService;
import com.upc.unistress.repositorios.RolRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(RolDTO rolDTO) {
        Rol rol = modelMapper.map(rolDTO, Rol.class);
        rolRepository.save(rol);
    }

    @Override
    public List<RolDTO> listar() {
        return rolRepository.findAll()
                .stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .toList();
    }


    @Override
    public void eliminar(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
    }

    @Override
    public RolDTO listId(Long id) {
        return rolRepository.findById(id)
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .orElseThrow(() -> new RuntimeException("Rol con ID " + id + " no encontrado"));
    }

    @Override
    public RolDTO findByTipoRol(String tipoRol) {
        return rolRepository.findByTipoRol(tipoRol)
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .orElseThrow(() -> new RuntimeException("Rol " + tipoRol + " no encontrado"));
    }
}
