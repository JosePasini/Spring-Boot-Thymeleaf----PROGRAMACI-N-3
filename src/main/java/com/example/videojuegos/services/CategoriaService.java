package com.example.videojuegos.services;

import com.example.videojuegos.entities.Categoria;
import com.example.videojuegos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements BaseService<Categoria>{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public List<Categoria> findAll() throws Exception {
        try{
            List<Categoria> entities = this.categoriaRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Categoria findById(long id) throws Exception {

        // Quizás tenga que sacar el IF.
        try{
            if (categoriaRepository.findById(id).isPresent()) {
                Optional<Categoria> categoria = this.categoriaRepository.findById(id);
                return categoria.get();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public Categoria saveOne(Categoria entity) throws Exception {
        try{
            Categoria categoria = this.categoriaRepository.save(entity);
            return categoria;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Categoria> optionalCategoria = this.categoriaRepository.findById(id);
            if (!optionalCategoria.isEmpty()) {
                Categoria categoria = optionalCategoria.get();
                categoria.setActivo(!categoria.isActivo());
                this.categoriaRepository.save(categoria);
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return true;
    }

    @Override
    @Transactional
    public Categoria updateOne(Categoria entity, long id) throws Exception {
        try{
            Optional<Categoria> optionalCategoria = this.categoriaRepository.findById(id);
            Categoria categoria = optionalCategoria.get();
            categoria = this.categoriaRepository.save(entity);
            return categoria;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
