package com.upc.unistress.servicios;

import com.upc.unistress.dtos.EstadisticaEmocionDTO;
import com.upc.unistress.dtos.EvolucionEmocionDTO;
import com.upc.unistress.interfaces.IEstadisticaEmocionalService;
import com.upc.unistress.repositorios.RegistroEmocionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class EstadisticaEmocionalService implements IEstadisticaEmocionalService {
    @Autowired
    private RegistroEmocionalRepository registroRepository;

    @Override
    public List<EstadisticaEmocionDTO> promedioNivelPorEmocion(Long usuarioId) {
        return registroRepository.promedioNivelPorEmocion(usuarioId)
                .stream()
                .map(obj -> new EstadisticaEmocionDTO(
                        (String) obj[0],
                        ((Double) obj[1])
                ))
                .toList();
    }

    @Override
    public List<EvolucionEmocionDTO> evolucionEmociones(Long usuarioId) {
        return registroRepository.evolucionEmociones(usuarioId)
                .stream()
                .map(obj -> new EvolucionEmocionDTO(
                        (LocalDate) ((java.time.LocalDateTime) obj[0]).toLocalDate(),
                        (String) obj[1],
                        (Integer) obj[2]
                ))
                .toList();
    }
    @Override
    public List<EvolucionEmocionDTO> evolucionEmocionesEntreFechas(Long usuarioId,
                                                                   LocalDateTime fechaInicio,
                                                                   LocalDateTime fechaFin) {
        return registroRepository.evolucionEmocionesEntreFechas(usuarioId, fechaInicio, fechaFin)
                .stream()
                .map(obj -> new EvolucionEmocionDTO(
                        ((java.time.LocalDateTime) obj[0]).toLocalDate(),
                        (String) obj[1],
                        (Integer) obj[2]
                ))
                .toList();
    }

}
