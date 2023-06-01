package com.juanpa.springfacilito.peliculas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juanpa.springfacilito.peliculas.entities.Genero;
import com.juanpa.springfacilito.peliculas.services.IGeneroService;

@RestController
public class GeneroController {

    private IGeneroService generoService;

    public GeneroController(IGeneroService generoServ) {
        this.generoService = generoServ;
    }

    @PostMapping("genero")
    public long guardar(@RequestParam String nombre) {
        Genero genero = new Genero();
        genero.setNombre(nombre);

        generoService.save(genero);

        return genero.getId();
    }

    @GetMapping("genero/{id}")
    public String buscarPorId(@PathVariable(name = "id") Long id) {
        return generoService.findById(id).getNombre();
    }
    
}
