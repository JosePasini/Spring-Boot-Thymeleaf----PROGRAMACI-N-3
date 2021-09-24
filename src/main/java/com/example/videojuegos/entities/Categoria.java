package com.example.videojuegos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Videojuego> videojuegoList_categoria;

}
