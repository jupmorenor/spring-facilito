package com.juanpa.springfacilito.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.juanpa.springfacilito.peliculas.entities.Genero;

public interface IGeneroRepository extends CrudRepository<Genero, Long> {
    
}
