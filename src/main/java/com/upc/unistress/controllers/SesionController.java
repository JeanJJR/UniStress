package com.upc.unistress.controllers;

import com.upc.unistress.dtos.SesionDTO;
import com.upc.unistress.interfaces.ISesionService;
import com.upc.unistress.servicios.SesionService;
import jakarta.persistence.GeneratedValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SesionController {

    @Autowired
    private ISesionService sesionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/sesion")
    public ResponseEntity<SesionDTO> crearSesion(@RequestBody SesionDTO sesion){
        SesionDTO nuevaSesionDTO = sesionService.crearSesion(sesion);
        return ResponseEntity.ok(nuevaSesionDTO);
    }

    @GetMapping("/sesiones")
    public ResponseEntity<List<SesionDTO>> obtenerSesiones(){
        return ResponseEntity.ok(sesionService.listarSesion());
    }

    @GetMapping("/sesion/{id}")
    public ResponseEntity<SesionDTO> obtenerSesionPorId(@PathVariable Long id){
        return ResponseEntity.ok(sesionService.buscarSesionporId(id));
    }
}