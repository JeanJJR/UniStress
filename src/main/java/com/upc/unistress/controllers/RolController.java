package com.upc.unistress.controllers;

import com.upc.unistress.dtos.RolDTO;
import com.upc.unistress.interfaces.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping
    public List<RolDTO> listar() {
        return rolService.list()
                .stream()
                .map(r -> new ModelMapper().map(r, RolDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody RolDTO dto) {
        rolService.insert(dto);
    }

    @PutMapping
    public void editar(@RequestBody RolDTO dto) {
        rolService.insert(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        rolService.delete(id);
    }

    @GetMapping("/{id}")
    public RolDTO listarId(@PathVariable("id") Long id) {
        return rolService.listId(id);
    }

    @GetMapping("/buscar")
    public RolDTO buscarPorNombre(@RequestParam String tipoRol) {
        return rolService.findByTipoRol(tipoRol);
    }
}