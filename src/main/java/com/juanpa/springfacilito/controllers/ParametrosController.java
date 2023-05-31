package com.juanpa.springfacilito.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.juanpa.springfacilito.models.Equipo;
import com.juanpa.springfacilito.models.Jugador;


@Controller
public class ParametrosController {

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

        Optional<Equipo> equipoOptional = getEquipos().stream().filter(
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


    private List<Equipo> getEquipos() {
        Equipo barcelona = new Equipo();
        barcelona.setNombre("Barcelona");
        barcelona.addJugador(new Jugador("TER STEJEN", 1));
        barcelona.addJugador(new Jugador("ARAUJO", 4));
        barcelona.addJugador(new Jugador("BUSQUETS", 5));
        barcelona.addJugador(new Jugador("LEWANDOWSKI", 9));
        barcelona.addJugador(new Jugador("DEMBELE", 7));
        
        
        Equipo realMadrid = new Equipo();
        realMadrid.setNombre("RealMadrid");
        realMadrid.addJugador(new Jugador("COURTOIS", 1));
        realMadrid.addJugador(new Jugador("CARVAJAL", 2));
        realMadrid.addJugador(new Jugador("MODRIC", 10));
        realMadrid.addJugador(new Jugador("BENZEMA", 9));
        realMadrid.addJugador(new Jugador("HAZARD", 7));

        List<Equipo> equipos = new ArrayList<Equipo>();
        equipos.add(barcelona);
        equipos.add(realMadrid);
        return equipos;
    }
    
}
