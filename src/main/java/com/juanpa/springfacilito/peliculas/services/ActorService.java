package com.juanpa.springfacilito.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanpa.springfacilito.peliculas.dao.IActorRepository;
import com.juanpa.springfacilito.peliculas.entities.Actor;

@Service
public class ActorService implements IActorService {
    
    @Autowired
    private IActorRepository repo;

    @Override
    public List<Actor> findAll() {
        return (List<Actor>) repo.findAll();
    }

    @Override
    public List<Actor> findAllById(List<Long> ids) {
        return (List<Actor>) repo.findAllById(ids);
    }
    
}
