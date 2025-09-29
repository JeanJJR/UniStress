package com.upc.unistress.servicios;

import com.upc.unistress.dtos.BancoPreguntaDTO;
import com.upc.unistress.entidades.BancoPregunta;
import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.IBancoPreguntaService;
import com.upc.unistress.repositorios.BancoPreguntaRepository;
import com.upc.unistress.repositorios.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoPreguntaService implements IBancoPreguntaService {

    @Autowired
    private BancoPreguntaRepository repository;



    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(BancoPreguntaDTO dto) {
        BancoPregunta pregunta = new BancoPregunta();
        pregunta.setEnunciado(dto.getEnunciado());

        Usuario psicologo = usuarioRepository.findById(dto.getPsicologoId())
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));
        pregunta.setPsicologo(psicologo);

        repository.save(pregunta);
    }


    @Override
    public List<BancoPreguntaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, BancoPreguntaDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BancoPreguntaDTO listId(Long id) {
        return repository.findById(id)
                .map(p -> modelMapper.map(p, BancoPreguntaDTO.class))
                .orElseThrow(() -> new RuntimeException("Pregunta con ID " + id + " no encontrada"));
    }





    @Override
    public List<BancoPreguntaDTO> listarPorPsicologo(Long psicologoId) {
        return repository.findByPsicologo_Id(psicologoId)
                .stream()
                .map(p -> modelMapper.map(p, BancoPreguntaDTO.class))
                .toList();
    }
}
