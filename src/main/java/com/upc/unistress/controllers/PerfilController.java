package com.upc.unistress.controllers;

import com.upc.unistress.dtos.PerfilDTO;
import com.upc.unistress.interfaces.IPerfilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;

    @GetMapping("/perfiles")
    public List<PerfilDTO> listar() {
        return perfilService.list()
                .stream()
                .map(p -> new ModelMapper().map(p, PerfilDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/perfil")
    public void registrar(@RequestBody PerfilDTO dto) {
        perfilService.insert(dto);
    }

    @PutMapping("/perfil")
    public void editar(@RequestBody PerfilDTO dto) {
        perfilService.insert(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        perfilService.delete(id);
    }

    @GetMapping("/{id}")
    public PerfilDTO listarId(@PathVariable("id") Long id) {
        return perfilService.listId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public Optional<PerfilDTO> buscarPorUsuario(@PathVariable("usuarioId") int usuarioId) {
        return perfilService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/por-tipo")
    public List<PerfilDTO> listarPorTipo(@RequestParam String tipoPerfil) {
        return perfilService.listarPorTipoPerfil(tipoPerfil);
    }
}