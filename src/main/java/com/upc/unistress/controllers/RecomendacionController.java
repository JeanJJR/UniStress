package com.upc.unistress.controllers;

import com.upc.unistress.dtos.RecomendacionDTO;
import com.upc.unistress.interfaces.IRecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    @Autowired
    private IRecomendacionService recomendacionService;

    @GetMapping
    public ResponseEntity<List<RecomendacionDTO>> listar() {
        return ResponseEntity.ok(recomendacionService.listar());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody RecomendacionDTO dto) {
        recomendacionService.insertar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Recomendación registrada correctamente");
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody RecomendacionDTO dto) {
        recomendacionService.insertar(dto);
        return ResponseEntity.ok("Recomendación actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        recomendacionService.eliminar(id);
        return ResponseEntity.ok("Recomendación eliminada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> listarId(@PathVariable Long id) {
        return ResponseEntity.ok(recomendacionService.listId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<RecomendacionDTO>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(recomendacionService.listarPorTipo(tipo));
    }
}
