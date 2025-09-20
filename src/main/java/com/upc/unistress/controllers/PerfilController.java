package com.upc.unistress.controllers;

import com.upc.unistress.dtos.PerfilDTO;
import com.upc.unistress.interfaces.IPerfilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PerfilDTO>> listar() {
        List<PerfilDTO> perfiles = perfilService.list()
                .stream()
                .map(p -> modelMapper.map(p, PerfilDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(perfiles);
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody PerfilDTO dto) {
        perfilService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Perfil registrado correctamente");
    }

    @PutMapping
    public ResponseEntity<String> editar(@RequestBody PerfilDTO dto) {
        perfilService.insert(dto);
        return ResponseEntity.ok("Perfil actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        perfilService.delete(id);
        return ResponseEntity.ok("Perfil eliminado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> listarId(@PathVariable("id") Long id) {
        PerfilDTO perfil = perfilService.listId(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Optional<PerfilDTO>> buscarPorUsuario(@PathVariable("usuarioId") int usuarioId) {
        Optional<PerfilDTO> perfil = perfilService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/por-tipo")
    public ResponseEntity<List<PerfilDTO>> listarPorTipo(@RequestParam String tipoPerfil) {
        List<PerfilDTO> perfiles = perfilService.listarPorTipoPerfil(tipoPerfil);
        return ResponseEntity.ok(perfiles);
    }
}
