package com.juanpa.springfacilito.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.juanpa.springfacilito.peliculas.entities.Actor;

public interface IActorRepository extends CrudRepository<Actor, Long> {
    
}
