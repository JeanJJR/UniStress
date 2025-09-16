package com.upc.unistress.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SesionDTO implements Serializable {
    private Long id;

    @NotNull(message = "La fecha no puede ser nula ")
    private LocalDate fecha;

    @NotNull (message = "La hora no puede ser nula")
    private LocalDate hora;

    @NotNull(message = "El estado no puede ser nulo")
    private String estado;

    @NotNull(message = "Debe estar asociado a un usuario ")
    private Long usuarioId;

    @NotNull(message = "Debe estar relaiconado con un psicologo")
    private Long psicologoId;
}
