package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuario_Id(Long usuarioId);
    List<Notificacion> findByEstado(String estado);
}
