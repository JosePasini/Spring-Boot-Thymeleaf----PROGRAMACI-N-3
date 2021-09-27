package com.example.videojuegos.services;

import com.example.videojuegos.entities.Estudio;
import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.repositories.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoService implements BaseService<Videojuego> {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Override
    @Transactional
    public List<Videojuego> findAll() throws Exception {
        try {
            List<Videojuego> entities = this.videojuegoRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego findById(long id) throws Exception {
        try {
            Optional<Videojuego> optionalVideojuego = this.videojuegoRepository.findById(id);
            return optionalVideojuego.get();
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
    public boolean deleteById(long id) throws Exception {
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
    public Videojuego updateOne(Videojuego entity, long id) throws Exception {
        try{
            Optional<Videojuego> estudioVideojuego = this.videojuegoRepository.findById(id);
                Videojuego videojuego = estudioVideojuego.get();
                videojuego = this.videojuegoRepository.save(entity);
                return videojuego;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Videojuego findByIdAndActivo(long id) throws Exception{
        try{
            Optional<Videojuego> optionalVideojuego = this.videojuegoRepository.findByIdAndActivo(id);
            return optionalVideojuego.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Videojuego> findAllByActivo() throws Exception{
        try{
            List<Videojuego> videojuegoList = this.videojuegoRepository.findAllByActivo();
            return videojuegoList;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Videojuego> findByTitulo(String filtro) throws Exception{
        try{

            List<Videojuego> entities = this.videojuegoRepository.findByTitulo(filtro);
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

}
