package com.juanpa.springfacilito.ejemplo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.juanpa.springfacilito.ejemplo.models.Equipo;
import com.juanpa.springfacilito.ejemplo.models.Jugador;

@Service
@Primary
public class EquiposInglaterraService implements IService {

    @Override
    public List<Equipo> getEquipos() {
        Equipo manchesterUnited = new Equipo();
        manchesterUnited.setNombre("ManchesterUnited");
        manchesterUnited.addJugador(new Jugador("GARMACHO", 49));
        
        Equipo manchesterCity = new Equipo();
        manchesterCity.setNombre("ManchesterCity");
        manchesterCity.addJugador(new Jugador("J ALVAREZ", 9));
 
        List<Equipo> equipos = new ArrayList<Equipo>();
        equipos.add(manchesterUnited);
        equipos.add(manchesterCity);
        return equipos;
    }
    
}
