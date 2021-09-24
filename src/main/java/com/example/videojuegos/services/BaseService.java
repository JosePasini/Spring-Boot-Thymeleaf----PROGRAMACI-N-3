package com.example.videojuegos.services;

import java.util.List;

public interface BaseService<E> {

    List<E> findAll () throws Exception;
    E findById(Long id) throws Exception;
    E saveOne(E entity) throws Exception;
    boolean deleteById (Long id) throws Exception;
    E updateOne (E entity, Long id) throws Exception;

}
