package com.juanpa.springfacilito.peliculas.services;

import java.util.List;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;

public interface IPeliculaService {
    public void save(Pelicula pelicula);
    public Pelicula findById(Long id);
    public void delete(Long id);
    public List<Pelicula> findAll();
}
