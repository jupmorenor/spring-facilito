package com.juanpa.springfacilito.peliculas.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.juanpa.springfacilito.peliculas.entities.Actor;
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
        Pelicula pelicula = peliculaService.findById(id);
        String ids = "";

        for (Actor actor : pelicula.getProtagonistas()) {
            if (ids.equals("")) {
                ids = actor.getId().toString();
            } else {
                ids = ids + "," + actor.getId().toString();
            }
        }
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("ids", ids);
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
        @RequestParam("archivo") MultipartFile imagen,
        RedirectAttributes attr
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

        if (ids != null && !ids.equals("")) {
            List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
            pelicula.setProtagonistas(actorService.findAllById(idsProtagonistas));
        }
        
        peliculaService.save(pelicula);
        attr.addAttribute("msj", "Catalogo actualizado");
        attr.addAttribute("tipoMsj", "success");
        return "redirect:home";
    }

    @GetMapping("/pelicula/{id}/delete")
    public String eliminar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes attr) {
        peliculaService.delete(id);
        attr.addAttribute("msj", "pelicula eliminada con éxito");
        attr.addAttribute("tipoMsj", "primary");
        return "redirect:/listado";
    }

    @GetMapping({"/", "/home", "/index"})
    public String home(
        Model model, 
        @RequestParam(name = "pagina", required = false, defaultValue = "0") Integer pagina,
        @RequestParam(required = false) String msj, 
        @RequestParam(required = false) String tipoMsj
    ) {
        PageRequest pr = PageRequest.of(pagina, 1);
        Page<Pelicula> page = peliculaService.findAll(pr);

        if (page.getTotalPages() > 0) {
            List<Integer> paginas = IntStream.rangeClosed(1, page.getTotalPages()).boxed().toList();
            model.addAttribute("paginas", paginas);
        }

        model.addAttribute("pactual", pagina + 1);

        model.addAttribute("peliculas", page.getContent());
        if (!"".equals(msj) && !"".equals(tipoMsj)) {
            model.addAttribute("msj", msj);
            model.addAttribute("tipoMsj", tipoMsj);
        }
        return "home";
    }

    @GetMapping("listado")
    public String listado(Model model, @RequestParam(required = false) String msj, @RequestParam(required = false) String tipoMsj) {
        model.addAttribute("titulo", "Listado de peliculas");
        model.addAttribute("peliculas", peliculaService.findAll());

        if (!"".equals(msj) && !"".equals(tipoMsj)) {
            model.addAttribute("msj", msj);
            model.addAttribute("tipoMsj", tipoMsj);
        }
        return "listado";
    }

    private String getExtension(String archivo) {
        return archivo.substring(archivo.lastIndexOf(archivo));
    }
    
}
