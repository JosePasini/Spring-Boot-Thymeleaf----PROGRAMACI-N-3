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
@Table(name = "estudio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudio extends BaseEntity{
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private boolean activo = true;

    // Relaciones

    @OneToMany(mappedBy = "estudio")
    private List<Videojuego> videojuegoList_estudio = new ArrayList<>();

}
