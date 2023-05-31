package com.juanpa.springfacilito.models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> plantilla;

    public Equipo() {
        plantilla = new ArrayList<Jugador>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(ArrayList<Jugador> plantilla) {
        this.plantilla = plantilla;
    }

    public void addJugador(Jugador jugador) {
        plantilla.add(jugador);
    }

}
