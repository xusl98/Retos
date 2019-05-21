/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.io.Serializable;

/**
 *
 * @author DIOS
 */
public class jugador implements Serializable{
    private String nombre;
    private  int puntos;
    private int ronda;

    public jugador(String nombre, int puntos, int ronda) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ronda = ronda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }
    
    
}
