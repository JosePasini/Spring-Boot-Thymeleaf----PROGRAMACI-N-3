package com.example.videojuegos.repositories;

import com.example.videojuegos.entities.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria, Long> {
}
