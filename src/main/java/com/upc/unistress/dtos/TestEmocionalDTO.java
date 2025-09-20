package com.upc.unistress.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestEmocionalDTO {
    private Long id;
    private Long usuarioId;
    private LocalDate fecha;
    private Integer puntajeTotal;
    private String nivelEstres;
    private List<PreguntaTestDTO> preguntas;
}