package com.upc.unistress.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recomendacion")
public class Recomendacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String mensaje;

    @Column(length = 50)
    private String tipo; // Ej: GENERAL, EMOCIONAL, BIENESTAR

    // Opcional: asociar a un registro emocional si la recomendación es específica
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_emocional_id")
    private RegistroEmocional registroEmocional;
}
