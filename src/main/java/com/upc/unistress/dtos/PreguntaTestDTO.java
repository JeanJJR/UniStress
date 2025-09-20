package com.upc.unistress.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaTestDTO {
    private Long id;
    private Long testId;
    private String pregunta;
    private String respuesta;
    private Integer puntaje;
}
