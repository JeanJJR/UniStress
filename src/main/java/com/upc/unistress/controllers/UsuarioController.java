package com.upc.unistress.controllers;

import com.upc.unistress.dtos.UsuarioDTO;
import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> listar() {
        return usuarioService.list()
                .stream()
                .map(u -> new ModelMapper().map(u, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/usuario")
    public void registrar(@RequestBody UsuarioDTO dto) {
        usuarioService.insert(dto);
    }

    @PutMapping("/usuario")
    public void editar(@RequestBody UsuarioDTO dto) {
        usuarioService.insert(dto); // save también actualiza si el id existe
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        usuarioService.delete(id);
    }

    @GetMapping("/{id}")
    public UsuarioDTO listarId(@PathVariable("id") Long id) {
        return usuarioService.listId(id);
    }

    @GetMapping("/buscar")
    public Optional<UsuarioDTO> buscarPorCorreo(@RequestParam String correo) {
        return usuarioService.findByCorreo(correo);
    }

    @GetMapping("/por-rol")
    public List<UsuarioDTO> listarPorRol(@RequestParam String tipoRol) {
        return usuarioService.listarPorRol(tipoRol);
    }
}
