package com.juanpa.springfacilito.ejemplo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juanpa.springfacilito.ejemplo.models.Equipo;
import com.juanpa.springfacilito.ejemplo.models.Jugador;

@Service("equiposEspaña")
public class EquipoService implements IService {
    
    public List<Equipo> getEquipos() {
        Equipo barcelona = new Equipo();
        barcelona.setNombre("Barcelona");
        barcelona.addJugador(new Jugador("TER STEJEN", 1));
        barcelona.addJugador(new Jugador("ARAUJO", 4));
        barcelona.addJugador(new Jugador("BUSQUETS", 5));
        barcelona.addJugador(new Jugador("LEWANDOWSKI", 9));
        barcelona.addJugador(new Jugador("MESSI", 10));
        barcelona.addJugador(new Jugador("DEMBELE", 7));
        
        
        Equipo realMadrid = new Equipo();
        realMadrid.setNombre("RealMadrid");
        realMadrid.addJugador(new Jugador("COURTOIS", 1));
        realMadrid.addJugador(new Jugador("CARVAJAL", 2));
        realMadrid.addJugador(new Jugador("MODRIC", 10));
        realMadrid.addJugador(new Jugador("JAMES", 11));
        realMadrid.addJugador(new Jugador("BENZEMA", 9));
        realMadrid.addJugador(new Jugador("HAZARD", 7));

        List<Equipo> equipos = new ArrayList<Equipo>();
        equipos.add(barcelona);
        equipos.add(realMadrid);
        return equipos;
    }
}
