package com.upc.unistress.servicios;

import com.upc.unistress.dtos.MetodoPagoDTO;
import com.upc.unistress.entidades.MetodoPago;
import com.upc.unistress.interfaces.IMetodoPagoService;
import com.upc.unistress.repositorios.MetodoPagoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoService implements IMetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void insertar(MetodoPagoDTO dto) {
        MetodoPago metodoPago = modelMapper.map(dto, MetodoPago.class);
        metodoPagoRepository.save(metodoPago);
    }

    @Override
    public List<MetodoPagoDTO> listar() {
        return metodoPagoRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MetodoPagoDTO.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (metodoPagoRepository.existsById(id)) {
            metodoPagoRepository.deleteById(id);
        }
    }

    @Override
    public MetodoPagoDTO listId(Long id) {
        return metodoPagoRepository.findById(id)
                .map(m -> modelMapper.map(m, MetodoPagoDTO.class))
                .orElseThrow(() -> new RuntimeException("Método de pago con ID " + id + " no encontrado"));
    }
}
