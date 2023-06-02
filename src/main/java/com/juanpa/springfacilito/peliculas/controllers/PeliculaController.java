package com.juanpa.springfacilito.peliculas.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;
import com.juanpa.springfacilito.peliculas.services.IActorService;
import com.juanpa.springfacilito.peliculas.services.IGeneroService;
import com.juanpa.springfacilito.peliculas.services.IPeliculaService;

import jakarta.validation.Valid;

@Controller
public class PeliculaController {

    private IPeliculaService peliculaService;
    private IGeneroService generoService;
    private IActorService actorService;

    public PeliculaController(
        IPeliculaService peliService, 
        IGeneroService generoService,
        IActorService actorService
    ) {
        this.peliculaService = peliService;
        this.generoService = generoService;
        this.actorService = actorService;
    }

    @GetMapping("/pelicula")
    public String crear(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", this.generoService.findAll());
        model.addAttribute("actores", this.actorService.findAll());
        model.addAttribute("titulo", "Nueva pelicula");
        return "pelicula";
    }

    @GetMapping("/pelicula/{id}")
    public String editar(@PathVariable(name = "id") Long id, Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", this.generoService.findAll());
        model.addAttribute("actores", this.actorService.findAll());
        model.addAttribute("titulo", "Editar pelicula");
        return "pelicula";
    }

    @PostMapping("/pelicula")
    public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name = "ids") String ids, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("generos", this.generoService.findAll());
            model.addAttribute("actores", this.actorService.findAll());
            return "pelicula";
        }

        List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        pelicula.setProtagonistas(actorService.findAllById(idsProtagonistas));
        
        peliculaService.save(pelicula);
        return "redirect:home";
    }

    @GetMapping({"/", "/home", "/index"})
    public String home(Model model) {
        model.addAttribute("peliculas", peliculaService.findAll());
        model.addAttribute("msj", "Catalogo actualizado");
        model.addAttribute("tipoMsj", "success");
        return "home";
    }

    
}
