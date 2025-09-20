package com.upc.unistress.controllers;

import com.upc.unistress.dtos.MetodoPagoDTO;
import com.upc.unistress.interfaces.IMetodoPagoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private IMetodoPagoService metodoPagoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> listar() {
        List<MetodoPagoDTO> metodos = metodoPagoService.listar()
                .stream()
                .map(m -> modelMapper.map(m, MetodoPagoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(metodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> obtenerPorId(@PathVariable Long id) {
        MetodoPagoDTO dto = metodoPagoService.listId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody MetodoPagoDTO dto) {
        metodoPagoService.insertar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Método de pago registrado correctamente");
    }

    @PutMapping
    public ResponseEntity<String> editar(@RequestBody MetodoPagoDTO dto) {
        metodoPagoService.insertar(dto);
        return ResponseEntity.ok("Método de pago actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        metodoPagoService.eliminar(id);
        return ResponseEntity.ok("Método de pago eliminado correctamente");
    }
}
