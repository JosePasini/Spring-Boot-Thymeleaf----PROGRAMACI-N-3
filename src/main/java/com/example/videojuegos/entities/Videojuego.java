package com.example.videojuegos.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String titulo;

    @Column(name = "ruta_img")
    private String ruta_img;
    @Column(name = "precio")
    private float precio;
    @Column(name = "cantidad")
    private short cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "oferta")
    private boolean oferta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lanzamiento")
    private Date lanzamiento;
    @Column(name = "activo")
    private boolean activo = true;

    // Relaciones

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria", nullable = false)
    @ToString.Exclude
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    @ToString.Exclude
    private Estudio estudio;


}
