package com.upc.unistress.controllers;

import com.upc.unistress.dtos.NivelPreguntaDTO;
import com.upc.unistress.interfaces.INivelPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/niveles-preguntas")
public class NivelPreguntaController {

    @Autowired
    private INivelPreguntaService service;

    @GetMapping
    public ResponseEntity<List<NivelPreguntaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody NivelPreguntaDTO dto) {
        service.insertar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nivel registrado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Nivel eliminado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelPreguntaDTO> listarId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listId(id));
    }
}
