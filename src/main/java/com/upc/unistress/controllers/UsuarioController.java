package com.upc.unistress.controllers;

import com.upc.unistress.entidades.Psicologo;
import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.IUsuarioService;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PropertyResolverUtils propertyResolverUtils;

    @PostMapping("/insertarUsuario")
    public Usuario Insertar(@RequestBody Usuario usuario) {
        return usuarioService.insertar(usuario);
    }

    @PutMapping ("/actualizarUsuario")
    public Usuario actualizar(@RequestBody Usuario usuario ){
        return usuarioService.actualizar(usuario);
    }

    @DeleteMapping("/Usuario/{idUsuario}")
    public Usuario eliminar(@PathVariable Long idUsuario){
        return usuarioService.eliminar(idUsuario);
    }

    @GetMapping("/Usuarios")
    public List<Usuario> listarTodos(){
        return usuarioService.listarTodos();
    }
}
