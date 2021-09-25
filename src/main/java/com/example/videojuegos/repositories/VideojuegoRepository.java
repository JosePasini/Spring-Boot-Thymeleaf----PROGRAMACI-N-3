package com.example.videojuegos.repositories;

import com.example.videojuegos.entities.Videojuego;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideojuegoRepository extends BaseRepository<Videojuego, Long> {
    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.activo = true", nativeQuery = true)
    List<Videojuego> findAllByActivo();

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.id = :id AND videojuegos.activo = true", nativeQuery = true)
    Optional<Videojuego> findByIdAndActivo(@Param("id") Long id);

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.titulo LIKE %:q% AND videojuegos.activo = true", nativeQuery = true)
    List<Videojuego> findByTitulo(@Param("q") String q);

}
