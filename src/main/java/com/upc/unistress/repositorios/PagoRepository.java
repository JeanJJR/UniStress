package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findBySuscripcion_Id(Long suscripcionId);
    // Pagos de un usuario (via suscripción)
    @Query("SELECT p FROM Pago p WHERE p.suscripcion.usuario.id = :usuarioId")
    List<Pago> findByUsuarioId(Long usuarioId);

    // Pagos realizados en el último mes
    @Query("SELECT p FROM Pago p WHERE p.estado = 'PAGADO' AND p.fechaPago >= :fecha")
    List<Pago> findPagosUltimoMes(LocalDate fecha);

    // Total recaudado en el último mes (útil para reportes admin)
    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.estado = 'PAGADO' AND p.fechaPago >= :fecha")
    Double obtenerTotalRecaudadoUltimoMes(LocalDate fecha);
}