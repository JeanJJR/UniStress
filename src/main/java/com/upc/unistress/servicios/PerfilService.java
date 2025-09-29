package com.upc.unistress.servicios;

import com.upc.unistress.dtos.PerfilDTO;
import com.upc.unistress.dtos.PerfilDetalleDTO;
import com.upc.unistress.entidades.Perfil;
import com.upc.unistress.entidades.Suscripcion;
import com.upc.unistress.entidades.Usuario;
import com.upc.unistress.interfaces.IPerfilService;
import com.upc.unistress.repositorios.PerfilRepository;
import com.upc.unistress.repositorios.SuscripcionRepository;
import com.upc.unistress.repositorios.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService implements IPerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public void insert(PerfilDTO perfilDTO) {
        Perfil perfil = modelMapper.map(perfilDTO, Perfil.class);
        perfilRepository.save(perfil);
    }

    @Override
    public List<PerfilDTO> list() {
        return perfilRepository.findAll()
                .stream()
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
        }
    }

    @Override
    public PerfilDTO listId(Long id) {
        return perfilRepository.findById(id)
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .orElseThrow(() -> new RuntimeException("Perfil con ID " + id + " no encontrado"));
    }

    @Override
    public Optional<PerfilDTO> findByUsuarioId(int usuarioId) {
        return perfilRepository.findByUsuario_Id(usuarioId)
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class));
    }

    @Override
    public List<PerfilDTO> listarPorTipoPerfil(String tipoPerfil) {
        return perfilRepository.findByTipoPerfil(tipoPerfil)
                .stream()
                .map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
                .toList();
    }
    @Override
    public void actualizarFoto(Long perfilId, String fotoUrl){
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        perfil.setFotoUrl(fotoUrl);
        perfilRepository.save(perfil);
    }
    // actualziar foto real
    @Override
    public void actualizarFoto(Long perfilId, MultipartFile archivo) {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID " + perfilId));

        try {
            // Carpeta local donde guardar fotos
            String uploadDir = "uploads/";
            String fileName = perfilId + "_" + archivo.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            // Crear directorio si no existe
            Files.createDirectories(filePath.getParent());

            // Guardar archivo
            Files.write(filePath, archivo.getBytes());

            // Guardar la URL relativa en BD
            perfil.setFotoUrl("/uploads/" + fileName);
            perfilRepository.save(perfil);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la foto", e);
        }
    }
    @Override
    public PerfilDetalleDTO obtenerPerfilPorUsuario(int usuarioId) {
        // Buscar el usuario
        Usuario usuario = usuarioRepository.findById((long) usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID " + usuarioId));

        // Buscar el perfil
        Perfil perfil = perfilRepository.findByUsuario_Id(usuarioId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para el usuario " + usuarioId));

        // Mapear al DTO
        PerfilDetalleDTO dto = new PerfilDetalleDTO();
        dto.setUsuarioId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setCorreo(usuario.getCorreo());
        dto.setContraseña(usuario.getContraseña());
        dto.setTelefono(usuario.getTelefono());

        dto.setTipoPerfil(perfil.getTipoPerfil());
        dto.setUniversidad(perfil.getUniversidad());
        dto.setCarrera(perfil.getCarrera());
        dto.setCiclo(perfil.getCiclo());
        dto.setEstadoAcademico(perfil.getEstadoAcademico());
        dto.setEspecialidad(perfil.getEspecialidad());
        dto.setColegiatura(perfil.getColegiatura());
        dto.setAñosExperiencia(perfil.getAñosExperiencia());
        dto.setFotoUrl(perfil.getFotoUrl());




        return dto;
    }

    @Override
    public void actualizarPerfil(int usuarioId, PerfilDetalleDTO dto) {
        // Buscar usuario existente
        Usuario usuario = usuarioRepository.findById((long) usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar si el correo ya pertenece a otro usuario
        if (!usuario.getCorreo().equals(dto.getCorreo())) {
            boolean correoExiste = usuarioRepository.findByCorreo(dto.getCorreo())
                    .filter(u -> !u.getId().equals(usuario.getId()))
                    .isPresent();
            if (correoExiste) {
                throw new RuntimeException("El correo ya está en uso por otro usuario");
            }
        }

        // Actualizar datos básicos
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContraseña(dto.getContraseña());
        usuario.setTelefono(dto.getTelefono());
        usuarioRepository.save(usuario);

        // Buscar perfil existente
        Perfil perfil = perfilRepository.findByUsuario_Id(usuarioId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        perfil.setTipoPerfil(dto.getTipoPerfil());
        perfil.setUniversidad(dto.getUniversidad());
        perfil.setCarrera(dto.getCarrera());
        perfil.setCiclo(dto.getCiclo());
        perfil.setEstadoAcademico(dto.getEstadoAcademico());
        perfil.setEspecialidad(dto.getEspecialidad());
        perfil.setColegiatura(dto.getColegiatura());
        perfil.setAñosExperiencia(dto.getAñosExperiencia());


        perfilRepository.save(perfil);
    }






}
