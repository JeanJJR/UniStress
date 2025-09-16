package com.upc.unistress.servicios;

import com.upc.unistress.entidades.Psicologo;
import com.upc.unistress.interfaces.IPsicologoService;
import com.upc.unistress.repositorios.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsicologoService implements IPsicologoService {
    @Autowired
    private PsicologoRepository psicologoRepository;

    @Override
    public Psicologo insertar(Psicologo psicologo) {
        return psicologoRepository.save(psicologo);
    }

    @Override
    public Psicologo actualizar(Psicologo psicologo){
        if(psicologoRepository.existsById(psicologo.getIdPsicologo())){
            return psicologoRepository.save(psicologo);
        }
        return  null;
    }

    @Override
    public void eliminar(Long idPsicologo){
        if(psicologoRepository.existsById(idPsicologo)) {
            psicologoRepository.deleteById(idPsicologo);
        } else {
            throw new RuntimeException("No existe el psicologo");
        }
    }

    @Override
    public List<Psicologo> listarTodos(){
        return  psicologoRepository.findAll();
    }

    @Override
    public Psicologo buscarPorId(Long idPsicologo){
        return psicologoRepository.findById(idPsicologo).orElseThrow(()-> new RuntimeException("Psicologo no encontrado"));
    }

    @Override
    public List<Psicologo> buscarPorNombrePsicologo(String palabra){
        return psicologoRepository.findAllByNombreContainingIgnoreCase(palabra);
    }
}
