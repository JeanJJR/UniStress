package com.upc.unistress.servicios;

import com.upc.unistress.dtos.UsuarioDTO;
import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.IUsuarioService;
import com.upc.unistress.repositorios.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insert(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioDTO> list() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }
    }

    @Override
    public UsuarioDTO listId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    @Override
    public Optional<UsuarioDTO> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class));
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public List<UsuarioDTO> listarPorRol(String tipoRol) {
        return usuarioRepository.findByRol_TipoRol(tipoRol)
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .toList();
    }
}
