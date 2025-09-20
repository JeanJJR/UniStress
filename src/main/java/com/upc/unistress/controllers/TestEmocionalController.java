package com.upc.unistress.controllers;

import com.upc.unistress.dtos.TestEmocionalDTO;
import com.upc.unistress.interfaces.ITestEmocionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tests-emocionales")
public class TestEmocionalController {

    @Autowired
    private ITestEmocionalService service;

    @GetMapping
    public ResponseEntity<List<TestEmocionalDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody TestEmocionalDTO dto) {
        service.insertar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Test emocional registrado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Test emocional eliminado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestEmocionalDTO> listarId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TestEmocionalDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<TestEmocionalDTO>> listarPorRangoFechas(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(service.listarPorRangoFechas(inicio, fin));
    }
}
