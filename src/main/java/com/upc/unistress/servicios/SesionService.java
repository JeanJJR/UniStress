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
        // Validar que el psicólogo existe
        Usuario psicologo = usuarioRepository.findById(dto.getPsicologoId())
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));
        // Validar que el estudiante existe
        Usuario estudiante = usuarioRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Validar roles
        if (!"PSICOLOGO".equalsIgnoreCase(psicologo.getRol().getTipoRol())) {
            throw new RuntimeException("El usuario con ID " + dto.getPsicologoId() + " no es un psicólogo válido.");
        }

        if (!"ESTUDIANTE".equalsIgnoreCase(estudiante.getRol().getTipoRol())) {
            throw new RuntimeException("El usuario con ID " + dto.getEstudianteId() + " no es un estudiante válido.");
        }

        //Crear sesion si todo está OK
        Sesion sesion = new Sesion();
        sesion.setPsicologo(psicologo);
        sesion.setEstudiante(estudiante);
        sesion.setFecha(dto.getFecha());
        sesion.setHora(dto.getHora());
        sesion.setObservaciones(dto.getObservaciones());
        sesion.setMensaje (dto.getMensaje());

        sesionRepository.save(sesion);

        // enviar notificacion de sesion para el  psicologo
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuario(psicologo);
        notificacion.setMensaje("Nueva sesión agendada con el estudiante " + estudiante.getNombre());
        notificacion.setTipo("CITA");
        notificacion.setFechaEnvio(LocalDate.now());
        notificacion.setEstado("PENDIENTE");
        notificacionRepository.save(notificacion);
    }

    //Listar sesion
    @Override
    public List<SesionDTO> listar() {
        return sesionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<SesionDTO> listarPorEstudianteYRango(Long estudianteId, LocalDate fechaInicio, LocalDate fechaFin) {
        return sesionRepository.findByEstudiante_Id(estudianteId)
                .stream()
                .filter(s -> !s.getFecha().isBefore(fechaInicio) && !s.getFecha().isAfter(fechaFin))
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<SesionDTO> listarHistorialPorEstudiante(Long estudianteId) {
        return sesionRepository.findByEstudiante_Id(estudianteId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public void editarSesion(Long id, SesionDTO dto) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesion no encontrada"));

        sesion.setFecha(dto.getFecha());
        sesion.setHora(dto.getHora());
        sesion.setObservaciones(dto.getObservaciones());

        sesionRepository.save(sesion);
    }


    //Cancelar sesión
    @Override
    public void eliminar(Long id) {
        if (sesionRepository.existsById(id)) {
            sesionRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Sesion no encontrada");
        }
    }



    private SesionDTO convertirADTO(Sesion s) {
        SesionDTO dto = modelMapper.map(s, SesionDTO.class);
        dto.setPsicologoId(s.getPsicologo().getId());
        dto.setEstudianteId(s.getEstudiante().getId());
        return dto;
    }
}
