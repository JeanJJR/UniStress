package com.upc.unistress.interfaces;

import com.upc.unistress.entidades.Usuario;

import java.util.List;

public interface IUsuarioService {
    public Usuario insertar(Usuario usuario);
    public Usuario actualizar(Usuario usuario);
    public Usuario eliminar(Long idUsuario);
    public List<Usuario> listarTodos();

}
