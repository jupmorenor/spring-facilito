package com.juanpa.springfacilito.peliculas.services;

import java.util.List;

import com.juanpa.springfacilito.peliculas.entities.Genero;

public interface IGeneroService {
    public void save(Genero genero);
    public Genero findById(Long id);
    public void delete(Long id);
    public List<Genero> findAll();
}
