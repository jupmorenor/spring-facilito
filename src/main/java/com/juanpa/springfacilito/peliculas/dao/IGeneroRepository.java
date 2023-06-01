package com.juanpa.springfacilito.peliculas.dao;

import com.juanpa.springfacilito.peliculas.entities.Genero;

public interface IGeneroRepository {

    public void save(Genero genero);
    public Genero findById(Long id);
    
}
