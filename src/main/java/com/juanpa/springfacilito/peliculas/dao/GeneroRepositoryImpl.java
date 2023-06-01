package com.juanpa.springfacilito.peliculas.dao;

import org.springframework.stereotype.Repository;

import com.juanpa.springfacilito.peliculas.entities.Genero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class GeneroRepositoryImpl implements IGeneroRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Genero genero) {
        entityManager.persist(genero);
    }

    @Override
    @Transactional
    public Genero findById(Long id) {
        return entityManager.find(Genero.class, id);
    }
    
}
