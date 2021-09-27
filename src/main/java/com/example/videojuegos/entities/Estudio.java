package com.example.videojuegos.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private boolean activo = true;

    // Relaciones
/*
    @OneToMany(mappedBy = "estudio")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Videojuego> videojuegoList_estudio;
*/
}
