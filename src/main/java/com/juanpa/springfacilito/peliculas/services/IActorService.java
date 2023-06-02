package com.juanpa.springfacilito.peliculas.services;

import java.util.List;

import com.juanpa.springfacilito.peliculas.entities.Actor;

public interface IActorService {
    public List<Actor> findAll();
    public List<Actor> findAllById(List<Long> ids);
}
