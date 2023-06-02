package com.juanpa.springfacilito.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanpa.springfacilito.peliculas.dao.IPeliculaRepository;
import com.juanpa.springfacilito.peliculas.entities.Pelicula;

@Service
public class PeliculaService implements IPeliculaService {

    @Autowired
    private IPeliculaRepository peliRepository;

    @Override
    public void save(Pelicula pelicula) {
        peliRepository.save(pelicula);
    }

    @Override
    public Pelicula findById(Long id) {
        return peliRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        peliRepository.deleteById(id);
    }

    @Override
    public List<Pelicula> findAll() {
        return (List<Pelicula>) peliRepository.findAll();
    }
    
}
