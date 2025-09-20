package com.upc.unistress.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    private Long id;
    private Long usuarioId;
    private String mensaje;
    private String tipo;
    private LocalDate fechaEnvio;
    private String estado;
}
