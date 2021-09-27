package com.example.videojuegos.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private boolean activo;


    // Relaciones
/*
    @OneToMany(mappedBy = "categoria")
    @ToString.Exclude
    private List<Videojuego> videojuegoList_categoria;
*/

}
