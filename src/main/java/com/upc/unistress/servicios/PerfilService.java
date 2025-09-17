package com.upc.unistress.servicios;

import com.upc.unistress.dtos.PerfilDTO;
import com.upc.unistress.entidades.Perfil;
import com.upc.unistress.interfaces.IPerfilService;
import com.upc.unistress.repositorios.PerfilRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService implements IPerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insert(PerfilDTO perfilDTO) {
        Perfil perfil = modelMapper.map(perfilDTO, Perfil.class);
        perfilRepository.save(perfil);
    }

    @Override
    public List<PerfilDTO> list() {
        return perfilRepository.findAll()
                .stream()
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
        }
    }

    @Override
    public PerfilDTO listId(Long id) {
        return perfilRepository.findById(id)
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .orElseThrow(() -> new RuntimeException("Perfil con ID " + id + " no encontrado"));
    }

    @Override
    public Optional<PerfilDTO> findByUsuarioId(int usuarioId) {
        return perfilRepository.findByUsuario_Id(usuarioId)
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class));
    }

    @Override
    public List<PerfilDTO> listarPorTipoPerfil(String tipoPerfil) {
        return perfilRepository.findByTipoPerfil(tipoPerfil)
                .stream()
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .toList();
    }
}
