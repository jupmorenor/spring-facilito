package com.juanpa.springfacilito.peliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long>{
    
}
