package com.upc.unistress.servicios;

import com.upc.unistress.dtos.TestEmocionalDTO;
import com.upc.unistress.entidades.TestEmocional;
import com.upc.unistress.interfaces.ITestEmocionalService;
import com.upc.unistress.repositorios.TestEmocionalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TestEmocionalService implements ITestEmocionalService {

    @Autowired
    private TestEmocionalRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(TestEmocionalDTO dto) {
        TestEmocional test = modelMapper.map(dto, TestEmocional.class);
        repository.save(test);
    }

    @Override
    public List<TestEmocionalDTO> listar() {
        return repository.findAll()
                .stream()
                .map(test -> modelMapper.map(test, TestEmocionalDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TestEmocionalDTO listId(Long id) {
        return repository.findById(id)
                .map(test -> modelMapper.map(test, TestEmocionalDTO.class))
                .orElseThrow(() -> new RuntimeException("Test emocional con ID " + id + " no encontrado"));
    }

    @Override
    public List<TestEmocionalDTO> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuario_Id(usuarioId)
                .stream()
                .map(test -> modelMapper.map(test, TestEmocionalDTO.class))
                .toList();
    }

    @Override
    public List<TestEmocionalDTO> listarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return repository.findByFechaBetween(inicio, fin)
                .stream()
                .map(test -> modelMapper.map(test, TestEmocionalDTO.class))
                .toList();
    }
}