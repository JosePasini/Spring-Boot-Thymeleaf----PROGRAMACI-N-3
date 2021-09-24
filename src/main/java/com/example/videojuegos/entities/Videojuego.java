package com.example.videojuegos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "videojuego")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego extends BaseEntity{

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
    @Column(name = "lanzamiento")
    private Date lanzamiento;
    @Column(name = "activo")
    private boolean activo = true;

    // Relaciones

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_estudio")
    private Estudio estudio;


}
