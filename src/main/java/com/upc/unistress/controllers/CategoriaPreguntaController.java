package com.upc.unistress.controllers;

import com.upc.unistress.dtos.CategoriaPreguntaDTO;
import com.upc.unistress.interfaces.ICategoriaPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias-preguntas")
public class CategoriaPreguntaController {

    @Autowired
    private ICategoriaPreguntaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaPreguntaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody CategoriaPreguntaDTO dto) {
        service.insertar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoría registrada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Categoría eliminada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaPreguntaDTO> listarId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listId(id));
    }
}