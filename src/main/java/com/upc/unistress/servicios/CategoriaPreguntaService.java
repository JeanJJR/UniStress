package com.upc.unistress.servicios;

import com.upc.unistress.dtos.CategoriaPreguntaDTO;
import com.upc.unistress.entidades.CategoriaPregunta;
import com.upc.unistress.interfaces.ICategoriaPreguntaService;
import com.upc.unistress.repositorios.CategoriaPreguntaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaPreguntaService implements ICategoriaPreguntaService {

    @Autowired
    private CategoriaPreguntaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(CategoriaPreguntaDTO dto) {
        CategoriaPregunta categoria = modelMapper.map(dto, CategoriaPregunta.class);
        repository.save(categoria);
    }

    @Override
    public List<CategoriaPreguntaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, CategoriaPreguntaDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CategoriaPreguntaDTO listId(Long id) {
        return repository.findById(id)
                .map(c -> modelMapper.map(c, CategoriaPreguntaDTO.class))
                .orElseThrow(() -> new RuntimeException("Categoría con ID " + id + " no encontrada"));
    }
}