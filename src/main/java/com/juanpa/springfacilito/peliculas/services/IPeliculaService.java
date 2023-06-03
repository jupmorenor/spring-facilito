package com.juanpa.springfacilito.peliculas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;

public interface IPeliculaService {
    public void save(Pelicula pelicula);
    public Pelicula findById(Long id);
    public void delete(Long id);
    public List<Pelicula> findAll();
    public Page<Pelicula> findAll(Pageable paginable);
}
