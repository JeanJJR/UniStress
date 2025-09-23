package com.upc.unistress.servicios;

import com.upc.unistress.dtos.SesionDTO;
import com.upc.unistress.entidades.Notificacion;
import com.upc.unistress.entidades.Sesion;
import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.ISesionService;
import com.upc.unistress.repositorios.NotificacionRepository;
import com.upc.unistress.repositorios.SesionRepository;
import com.upc.unistress.repositorios.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SesionService implements ISesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Crear Sesion 
    @Override
    public void crearSesion(SesionDTO dto) {
        Usuario psicologo = usuarioRepository.findById(dto.getPsicologoId())
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));
        Usuario estudiante = usuarioRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Sesion sesion = new Sesion();
        sesion.setPsicologo(psicologo);
        sesion.setEstudiante(estudiante);
        sesion.setFecha(dto.getFecha());
        sesion.setHora(dto.getHora());
        sesion.setObservaciones(dto.getObservaciones());

        sesionRepository.save(sesion);

        // enviar notificacion de sesion al psicologo
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuario(psicologo);
        notificacion.setMensaje("Nueva sesión agendada con el estudiante " + estudiante.getNombre());
        notificacion.setTipo("CITA");
        notificacion.setFechaEnvio(LocalDate.now());
        notificacion.setEstado("PENDIENTE");
        notificacionRepository.save(notificacion);
    }

    //Listar sesion - Historial de sesiones
    @Override
    public List<SesionDTO> listar() {
        return sesionRepository.findAll()
                .stream()
                .map(s -> {
                    SesionDTO dto = modelMapper.map(s, SesionDTO.class);
                    dto.setPsicologoId(s.getPsicologo().getId());
                    dto.setEstudianteId(s.getEstudiante().getId());
                    return dto;
                })
                .toList();
    }
    //Cancelar sesión
    @Override
    public void eliminar(Long id) {
        if (sesionRepository.existsById(id)) {
            sesionRepository.deleteById(id);
        }
    }

    //Filtro entre fechas 
    @Override
    public List<SesionDTO> listarPorFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        return sesionRepository.findByFechaBetween(fechaInicial, fechaFinal)
                .stream()
                .map(s -> {
                    SesionDTO dto = modelMapper.map(s, SesionDTO.class);
                    dto.setPsicologoId(s.getPsicologo().getId());
                    dto.setEstudianteId(s.getEstudiante().getId());
                    return dto;
                })
                .toList();
    }
}
