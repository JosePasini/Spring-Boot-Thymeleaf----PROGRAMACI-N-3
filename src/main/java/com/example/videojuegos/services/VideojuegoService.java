package com.example.videojuegos.services;

import com.example.videojuegos.entities.Estudio;
import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.repositories.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class VideojuegoService implements BaseService<Videojuego> {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Override
    @Transactional
    public List<Videojuego> findAll() throws Exception {
        try {
            List<Videojuego> entity = this.videojuegoRepository.findAll();
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego findById(Long id) throws Exception {
        try {
            Optional<Videojuego> optionalVideojuego = this.videojuegoRepository.findById(id);
            Videojuego videojuego = optionalVideojuego.get();
            return videojuego;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego saveOne(Videojuego entity) throws Exception {
        try {
            Videojuego videojuego = this.videojuegoRepository.save(entity);
            return videojuego;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            Optional<Videojuego> estudioVideojuego = this.videojuegoRepository.findById(id);
            if (!estudioVideojuego.isEmpty()) {
                Videojuego videojuego = estudioVideojuego.get();
                videojuego.setActivo(!videojuego.isActivo());
                this.videojuegoRepository.save(videojuego);
            } else {
                throw new Exception();
            }
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego updateOne(Videojuego entity, Long id) throws Exception {
        try{
            Optional<Videojuego> estudioVideojuego = this.videojuegoRepository.findById(id);
            if (!estudioVideojuego.isEmpty()){
                Videojuego videojuego = estudioVideojuego.get();
                videojuego = this.videojuegoRepository.save(entity);
                return videojuego;
            } else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
