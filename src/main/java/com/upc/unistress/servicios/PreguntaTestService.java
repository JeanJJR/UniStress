package com.upc.unistress.servicios;

import com.upc.unistress.dtos.PreguntaTestDTO;
import com.upc.unistress.entidades.PreguntaTest;
import com.upc.unistress.interfaces.IPreguntaTestService;
import com.upc.unistress.repositorios.PreguntaTestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaTestService implements IPreguntaTestService {

    @Autowired
    private PreguntaTestRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(PreguntaTestDTO dto) {
        PreguntaTest pregunta = modelMapper.map(dto, PreguntaTest.class);
        repository.save(pregunta);
    }

    @Override
    public List<PreguntaTestDTO> listar() {
        return repository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, PreguntaTestDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PreguntaTestDTO listId(Long id) {
        return repository.findById(id)
                .map(p -> modelMapper.map(p, PreguntaTestDTO.class))
                .orElseThrow(() -> new RuntimeException("Pregunta con ID " + id + " no encontrada"));
    }

    @Override
    public List<PreguntaTestDTO> listarPorTest(Long testId) {
        return repository.findByTest_Id(testId)
                .stream()
                .map(p -> modelMapper.map(p, PreguntaTestDTO.class))
                .toList();
    }
}