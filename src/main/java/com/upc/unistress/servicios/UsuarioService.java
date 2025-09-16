package com.upc.unistress.servicios;

import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.IUsuarioService;
import com.upc.unistress.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario insertar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Usuario usuario){
        if (usuarioRepository.findById(usuario.getIdUsuario()).isPresent()){
            return usuarioRepository.save(usuario);
        }
        return null;
    }
    @Override
    public Usuario eliminar(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        usuarioRepository.delete(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
