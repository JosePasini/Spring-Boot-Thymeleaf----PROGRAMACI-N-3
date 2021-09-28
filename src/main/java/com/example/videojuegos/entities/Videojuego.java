package com.example.videojuegos.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "videojuegos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo")
    @NotEmpty(message = "{NotEmpty.Videojuego.titulo}")
    private String titulo;

    @Column(name = "ruta_img")
    private String ruta_img;

    @Min(value = 5, message = "El precio mínimo debe ser de 5 dólares")
    @Max(value = 150, message = "El precio no puede exceder los 150 dólares")
    @Column(name = "precio")
    private float precio;

    @Min(value = 1, message = "El Stock mínimo debe ser 1")
    @Max(value = 1000, message = "El Stock no puede superar las 1000 unidades")
    @Column(name = "cantidad")
    private short cantidad;

    @Column(name = "descripcion")
    @Size(min=5, max=100, message = "La descripción debe ser entre 5 caracteres y 100 caracteres como como máximo")
    private String descripcion;

    @Column(name = "oferta")
    private boolean oferta;

    @NotNull(message = "Debe elegir una fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lanzamiento")
    private Date lanzamiento;

    @Column(name = "activo")
    private boolean activo = true;

    // Relaciones

    @NotNull(message = "La categoria es requerida")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria", nullable = false)
    @ToString.Exclude
    private Categoria categoria;

    @NotNull(message = "El estudio es requerido")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    @ToString.Exclude
    private Estudio estudio;


}
