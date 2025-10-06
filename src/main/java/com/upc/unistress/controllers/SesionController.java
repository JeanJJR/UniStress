package com.upc.unistress.controllers;

import com.upc.unistress.dtos.SesionDTO;
import com.upc.unistress.interfaces.ISesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    // 1. Agendar sesión
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody SesionDTO dto) {
        sesionService.crearSesion(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Sesión creada y notificación enviada al psicólogo");
    }
    // 2. Listar todas las sesiones (admin)
    @GetMapping
    public ResponseEntity<List<SesionDTO>> listar() {
        return ResponseEntity.ok(sesionService.listar());
    }

    // 3. Listar proximas sesiones de un estudiante en un rango de fechas
    @GetMapping("/estudiante/Start&EndDate{id}")
    public ResponseEntity<List<SesionDTO>> listarPorEstudianteYRango(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(sesionService.listarPorEstudianteYRango(id, fechaInicio, fechaFin));
    }

    // 4. Listar historial de sesiones
    @GetMapping("/historial/estudiante/{id}")
    public ResponseEntity<List<SesionDTO>> historialPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(sesionService.listarHistorialPorEstudiante(id));
    }

    // 5. Editar sesion

    @PutMapping("/{id}")
    public ResponseEntity<String> editarSesion (@PathVariable Long id, @RequestBody SesionDTO dto) {
        sesionService.editarSesion(id, dto);
        return ResponseEntity.ok("Sesión actualizada correctamente");
    }

    // 6. Cancelar sesión
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        sesionService.eliminar(id);
        return ResponseEntity.ok("Sesión eliminada correctamente");
    }
}
