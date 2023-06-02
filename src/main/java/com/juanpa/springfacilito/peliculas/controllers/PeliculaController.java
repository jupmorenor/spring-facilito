package com.juanpa.springfacilito.peliculas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;
import com.juanpa.springfacilito.peliculas.services.IGeneroService;
import com.juanpa.springfacilito.peliculas.services.IPeliculaService;

@Controller
public class PeliculaController {

    private IPeliculaService peliculaService;
    private IGeneroService generoService;

    public PeliculaController(IPeliculaService peliService, IGeneroService generoService) {
        this.peliculaService = peliService;
        this.generoService = generoService;
    }

    @GetMapping("/pelicula")
    public String crear(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", this.generoService.findAll());
        model.addAttribute("titulo", "Nueva pelicula");
        return "pelicula";
    }

    @GetMapping("/pelicula/{id}")
    public String editar(@PathVariable(name = "id") Long id, Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", this.generoService.findAll());
        model.addAttribute("titulo", "Editar pelicula");
        return "pelicula";
    }

    @PostMapping("/pelicula")
    public String guardar(Pelicula pelicula) {
        peliculaService.save(pelicula);
        return "redirect:home";
    }

    @GetMapping({"/", "/home", "/index"})
    public String home() {
        return "home";
    }

    
}
