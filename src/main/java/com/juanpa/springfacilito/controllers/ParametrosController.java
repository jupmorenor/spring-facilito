package com.juanpa.springfacilito.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.juanpa.springfacilito.models.Equipo;
import com.juanpa.springfacilito.models.Jugador;
import com.juanpa.springfacilito.services.IService;


@Controller
public class ParametrosController {

    private IService equipoService;

    public ParametrosController(@Qualifier("equiposEspaña") IService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping(value = "/parametros")
    public String parametros(
        @RequestParam(defaultValue = "no param") String valor, 
        @RequestParam(defaultValue = "", name = "valor_dos") String otroValor, 
        Model model
    ) {

        model.addAttribute("titulo", "Parametros");
        model.addAttribute("parametro", valor);
        model.addAttribute("otroParametro", otroValor);

        return "parametros";
    }

    @GetMapping("equipos/{nombre}/{numero}")
    public String parametrosPorPath(
        @PathVariable String nombre, 
        @PathVariable("numero") Integer numero,
        Model model
    ) {

        Optional<Equipo> equipoOptional = equipoService.getEquipos().stream().filter(
            eq -> nombre.toLowerCase().equals(eq.getNombre().toLowerCase())
        ).findFirst();

        if (equipoOptional.isPresent()) {
            Optional<Jugador> jugadorOptional = equipoOptional.get().getPlantilla().stream().filter(
                jug -> numero == jug.getNumero()
            ).findFirst();

            if (jugadorOptional.isPresent()) {
                model.addAttribute("jugador", jugadorOptional.get());
            }
        }

        return "parametros";
    }

}
