package com.juanpa.springfacilito.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    /*
     * Para Metodos GET
     */
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("titulo", "Soy el titulo");
        model.addAttribute("saludo", "Soy el saludo");
        model.addAttribute("show", false);
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Lista 1");
        lista.add("Lista 2");
        lista.add("Lista 3");
        model.addAttribute("lista", lista);
        return "index";
    }

    /*
     * Para especificar el metodo
     */
    @RequestMapping(value = "/index-req-mapping", method = RequestMethod.GET)
    public String indexRequestMapping(){
        return "index";
    }

    /*
     * Para metodo POST
     */
    @PostMapping(value = "/index-post")
    public String indexPost() {
        return "index";
    }

    @GetMapping(value = "/index-mv")
    public ModelAndView indexMV(ModelAndView mv) {
        mv.addObject("titulo", "Titulo de MV");
        mv.addObject("saludo", "Saludo de MV");
        mv.addObject("show", true);

        // List<String> lista = List.of();
        // mv.addObject("lista", getListado("Lista de MV 1", "Lista de MV 2", "Lista de MV 3"));

        mv.setViewName("index");

        return mv;
    }

    @ModelAttribute("lista")
    public ArrayList<String> getListado() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Lista de MV 1");
        lista.add("Lista de MV 2");
        lista.add("Lista de MV 3");
        return lista;
    }
    
}
