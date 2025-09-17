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
@Table(name = "rol")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_rol", nullable = false, length = 20, unique = true)
    private String tipoRol; // Ej: ADMIN, ESTUDIANTE, PSICOLOGO

    public Rol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
}