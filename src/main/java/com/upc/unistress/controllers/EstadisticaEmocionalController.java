package com.upc.unistress.controllers;

import com.upc.unistress.dtos.EstadisticaEmocionDTO;
import com.upc.unistress.dtos.EvolucionEmocionDTO;
import com.upc.unistress.interfaces.IEstadisticaEmocionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/estadisticas/emociones")
public class EstadisticaEmocionalController {

    @Autowired
    private IEstadisticaEmocionalService estadisticaService;

    // Promedio de nivel emocional por emoción
    @GetMapping("/promedio/{usuarioId}")
    public ResponseEntity<List<EstadisticaEmocionDTO>> promedioPorEmocion(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(estadisticaService.promedioNivelPorEmocion(usuarioId));
    }

    // Evolución de emociones en el tiempo
    @GetMapping("/evolucion/{usuarioId}")
    public ResponseEntity<List<EvolucionEmocionDTO>> evolucion(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(estadisticaService.evolucionEmociones(usuarioId));
    }
    // Evolucion de emociones filtradas por rango de fechas
    @GetMapping("/estadisticas/emociones/evolucion-rango/{usuarioId}")
    public ResponseEntity<List<EvolucionEmocionDTO>> evolucionEntreFechas(
            @PathVariable Long usuarioId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        return ResponseEntity.ok(estadisticaService.evolucionEmocionesEntreFechas(usuarioId, fechaInicio, fechaFin));
    }

}
