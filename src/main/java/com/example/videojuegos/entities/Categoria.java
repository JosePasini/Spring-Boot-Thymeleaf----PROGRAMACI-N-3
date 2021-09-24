package com.example.videojuegos.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria extends BaseEntity{

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private boolean activo;


    // Relaciones

    @OneToMany(mappedBy = "categoria")
    @ToString.Exclude
    private List<Videojuego> videojuegoList_categoria;

}
