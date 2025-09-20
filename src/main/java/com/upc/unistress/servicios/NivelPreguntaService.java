package com.upc.unistress.servicios;

import com.upc.unistress.dtos.NivelPreguntaDTO;
import com.upc.unistress.entidades.NivelPregunta;
import com.upc.unistress.interfaces.INivelPreguntaService;
import com.upc.unistress.repositorios.NivelPreguntaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelPreguntaService implements INivelPreguntaService {

    @Autowired
    private NivelPreguntaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(NivelPreguntaDTO dto) {
        NivelPregunta nivel = modelMapper.map(dto, NivelPregunta.class);
        repository.save(nivel);
    }

    @Override
    public List<NivelPreguntaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(n -> modelMapper.map(n, NivelPreguntaDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public NivelPreguntaDTO listId(Long id) {
        return repository.findById(id)
                .map(n -> modelMapper.map(n, NivelPreguntaDTO.class))
                .orElseThrow(() -> new RuntimeException("Nivel con ID " + id + " no encontrado"));
    }
}
