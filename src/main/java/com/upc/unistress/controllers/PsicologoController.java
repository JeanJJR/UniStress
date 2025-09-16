package com.upc.unistress.controllers;

import com.upc.unistress.entidades.Psicologo;
import com.upc.unistress.interfaces.IPsicologoService;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/psicologos")
public class PsicologoController {
    @Autowired
    private IPsicologoService psicologoService;

    @PostMapping("/ingresoPsicologo")
    public Psicologo insertar(@RequestBody Psicologo psicologo){
        return psicologoService.insertar(psicologo);
    }

    @PutMapping ("/actualizarPsicologo")
    public Psicologo actualizar(@RequestBody Psicologo psicologo ){
        return psicologoService.actualizar(psicologo);
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public Psicologo buscarPorId(@PathVariable Long idPsicologo) {
        return psicologoService.buscarPorId(idPsicologo);
    }

    @DeleteMapping("/psicologo/{idPsicologo}")
    public void eliminar(@PathVariable Long idPsicologo) {
        psicologoService.eliminar(idPsicologo);
    }

    @GetMapping("/psicologo/nombre/{palabra}")
    public List<Psicologo> buscarPorNombrePsicologo(@PathVariable String palabra){
        return psicologoService.buscarPorNombrePsicologo(palabra);
    }

    @GetMapping("/psicologo")
    public List<Psicologo> listarTodos() {
        return psicologoService.listarTodos();
    }
}
