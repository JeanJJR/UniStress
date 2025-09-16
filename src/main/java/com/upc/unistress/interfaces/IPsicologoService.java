package com.upc.unistress.interfaces;
import java.time.LocalDate;
import java.util.List;

import com.upc.unistress.entidades.Psicologo;

public interface IPsicologoService {
    public Psicologo insertar(Psicologo psicologo);
    public Psicologo actualizar(Psicologo psicologo);
    public void eliminar(Long idPsicologo);
    public Psicologo buscarPorId(Long idPsicologo);
    public List<Psicologo> listarTodos();
    public  List<Psicologo> buscarPorNombrePsicologo(String palabra);

}
