package com.upc.unistress.interfaces;

import com.upc.unistress.dtos.EstadisticaEmocionDTO;
import com.upc.unistress.dtos.EvolucionEmocionDTO;

import java.util.List;

public interface IEstadisticaEmocionalService {
    List<EstadisticaEmocionDTO> promedioNivelPorEmocion(Long usuarioId);
    List<EvolucionEmocionDTO> evolucionEmociones(Long usuarioId);
    List<EvolucionEmocionDTO> evolucionEmocionesEntreFechas(Long usuarioId,
                                                            java.time.LocalDateTime fechaInicio,
                                                            java.time.LocalDateTime fechaFin);

}
