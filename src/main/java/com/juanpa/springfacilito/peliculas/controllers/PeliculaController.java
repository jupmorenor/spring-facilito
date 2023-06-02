package com.juanpa.springfacilito.peliculas.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.juanpa.springfacilito.peliculas.entities.Pelicula;
import com.juanpa.springfacilito.peliculas.services.IActorService;
import com.juanpa.springfacilito.peliculas.services.IArchivoService;
import com.juanpa.springfacilito.peliculas.services.IGeneroService;
import com.juanpa.springfacilito.peliculas.services.IPeliculaService;

import jakarta.validation.Valid;

@Controller
public class PeliculaController {

    private IPeliculaService peliculaService;
    private IGeneroService generoService;
    private IActorService actorService;
    private IArchivoService archivoService;

    public PeliculaController(
        IPeliculaService peliService, 
        IGeneroService generoService,
        IActorService actorService,
        IArchivoService archivoService
    ) {
        this.peliculaService = peliService;
        this.generoService = generoService;
        this.actorService = actorService;
        this.archivoService = archivoService;
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
    public String guardar(
        @Valid Pelicula pelicula, 
        BindingResult br, 
        @ModelAttribute(name = "ids") String ids, 
        Model model,
        @RequestParam("archivo") MultipartFile imagen
    ) {

        if (br.hasErrors()) {
            model.addAttribute("generos", this.generoService.findAll());
            model.addAttribute("actores", this.actorService.findAll());
            return "pelicula";
        }

        if (!imagen.isEmpty()) {
            String archivo = pelicula.getNombre() + this.getExtension(imagen.getOriginalFilename());
            pelicula.setImagen(archivo);
            try {
                archivoService.guardar(archivo, imagen.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            pelicula.setImagen("default.jpg");
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

    private String getExtension(String archivo) {
        return archivo.substring(archivo.lastIndexOf(archivo));
    }
    
}
