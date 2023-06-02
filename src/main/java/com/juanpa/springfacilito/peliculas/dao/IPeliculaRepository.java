package com.juanpa.springfacilito.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;

public interface IPeliculaRepository extends CrudRepository<Pelicula, Long>{
    
}
