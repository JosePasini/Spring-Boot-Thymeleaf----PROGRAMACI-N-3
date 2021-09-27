package com.example.videojuegos.services;

import com.example.videojuegos.entities.Estudio;
import com.example.videojuegos.repositories.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EstudioService implements BaseService<Estudio> {

    @Autowired
    private EstudioRepository estudioRepository;

    @Override
    @Transactional
    public List<Estudio> findAll() throws Exception {
        try {
            List<Estudio> entity = this.estudioRepository.findAll();
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio findById(long id) throws Exception {
        try {
            Optional<Estudio> optionalEstudio = this.estudioRepository.findById(id);
            Estudio estudio = optionalEstudio.get();
            return estudio;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio saveOne(Estudio entity) throws Exception {
        try {
            Estudio estudio = this.estudioRepository.save(entity);
            return estudio;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Estudio> estudioOptional = this.estudioRepository.findById(id);
            if (!estudioOptional.isEmpty()) {
                Estudio estudio = estudioOptional.get();
                estudio.setActivo(!estudio.isActivo());
                this.estudioRepository.save(estudio);
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
    public Estudio updateOne(Estudio entity, long id) throws Exception {
        try{
            Optional<Estudio> estudioOptional = this.estudioRepository.findById(id);
                Estudio estudio = estudioOptional.get();
                estudio = this.estudioRepository.save(entity);
                return estudio;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
