package com.upc.unistress.controllers;

import com.upc.unistress.dtos.SesionDTO;
import com.upc.unistress.interfaces.ISesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    @Autowired
    private ISesionService sesionService;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SesionDTO dto) {
        sesionService.crearSesion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sesión creada y notificación enviada al psicólogo");
    }

    @GetMapping
    public ResponseEntity<List<SesionDTO>> listar() {
        return ResponseEntity.ok(sesionService.listar());
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<SesionDTO>> listarPorFecha(@PathVariable String fecha) {
        LocalDate fechaBuscada = LocalDate.parse(fecha);
        return ResponseEntity.ok(sesionService.listarPorFecha(fechaBuscada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        sesionService.eliminar(id);
        return ResponseEntity.ok("Sesión eliminada correctamente");
    }
}
