package com.upc.unistress.servicios;

import com.upc.unistress.dtos.RecomendacionDTO;
import com.upc.unistress.entidades.Recomendacion;
import com.upc.unistress.entidades.RegistroEmocional;
import com.upc.unistress.repositorios.RecomendacionRepository;
import com.upc.unistress.repositorios.RegistroEmocionalRepository;
import com.upc.unistress.interfaces.IRecomendacionService;
import com.upc.unistress.repositorios.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionService implements IRecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @Autowired
    private RegistroEmocionalRepository registroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(RecomendacionDTO dto) {
        Recomendacion recomendacion = modelMapper.map(dto, Recomendacion.class);
        if (dto.getRegistroEmocionalId() != null) {
            RegistroEmocional registro = registroRepository.findById(dto.getRegistroEmocionalId())
                    .orElseThrow(() -> new RuntimeException("Registro emocional no encontrado"));
            recomendacion.setRegistroEmocional(registro);
        }
        recomendacionRepository.save(recomendacion);
    }

    @Override
    public List<RecomendacionDTO> listar() {
        return recomendacionRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, RecomendacionDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        recomendacionRepository.deleteById(id);
    }

    @Override
    public RecomendacionDTO listId(Long id) {
        return recomendacionRepository.findById(id)
                .map(r -> modelMapper.map(r, RecomendacionDTO.class))
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada"));
    }

    @Override
    public List<RecomendacionDTO> listarPorTipo(String tipo) {
        return recomendacionRepository.findByTipo(tipo)
                .stream()
                .map(r -> modelMapper.map(r, RecomendacionDTO.class))
                .toList();
    }
    @Override
    public List<RecomendacionDTO> listarPorUsuario(Long usuarioId) {
        return recomendacionRepository.findByRegistroEmocional_Usuario_Id(usuarioId)
                .stream()
                .map(r -> {
                    RecomendacionDTO dto = new RecomendacionDTO();
                    dto.setId(r.getId());
                    dto.setMensaje(r.getMensaje());
                    dto.setTipo(r.getTipo());
                    dto.setRegistroEmocionalId(r.getRegistroEmocional().getId());
                    dto.setUsuarioId(r.getRegistroEmocional().getUsuario().getId());
                    return dto;
                })
                .toList();
    }


}
