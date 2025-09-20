package com.upc.unistress.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notificacion")
public class Notificacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // <-- ESTE ES EL ID OBLIGATORIO

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false, length = 50)
    private String tipo; // CITA, RECORDATORIO, GENERAL, etc.

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @Column(nullable = false, length = 20)
    private String estado; // PENDIENTE, LEIDA
}
