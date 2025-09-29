package com.upc.unistress.controllers;
import com.upc.unistress.dtos.BancoPreguntaDTO;
import com.upc.unistress.interfaces.IBancoPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco-preguntas")
public class BancoPreguntaController {

    @Autowired
    private IBancoPreguntaService service;

    @GetMapping
    public ResponseEntity<List<BancoPreguntaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody BancoPreguntaDTO dto) {
        service.insertar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pregunta registrada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Pregunta eliminada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoPreguntaDTO> listarId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listId(id));
    }


    @GetMapping("/psicologo/{psicologoId}")
    public ResponseEntity<List<BancoPreguntaDTO>> listarPorPsicologo(@PathVariable Long psicologoId) {
        return ResponseEntity.ok(service.listarPorPsicologo(psicologoId));
    }
}