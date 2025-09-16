package com.upc.unistress.servicios;
import com.upc.unistress.dtos.SesionDTO;
import com.upc.unistress.entidades.Sesion;
import com.upc.unistress.interfaces.ISesionService;
import com.upc.unistress.repositorios.PsicologoRepository;
import com.upc.unistress.repositorios.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class SesionService implements ISesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SesionDTO crearSesion(SesionDTO sesionDTO) {
        Sesion sesion = modelMapper.map(sesionDTO, Sesion.class);
        return modelMapper.map(sesionRepository.save(sesion),SesionDTO.class);

    }

    @Override
    public List<SesionDTO> listarSesion(){
        return sesionRepository.findAll()
                .stream()
                .map(sesion -> modelMapper.map(sesion,SesionDTO.class))
                .toList();

    }

    @Override
    public SesionDTO buscarSesionporId(Long id){
        return sesionRepository.findById(id)
                .map(sesion -> modelMapper.map(sesion,SesionDTO.class))
                .orElseThrow(() -> new RuntimeException("Sesion con ID"+ "id"+ "no encontrado"));
    }
}
