/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.io.Serializable;

/**
 *
 * @author javier 
 * @version 1.1
 */
public class jugador implements Serializable{
     /**
     * atributos de jugador 
     * nombre que es un string  
    * puntos que es un int
    * ronda que es un int
    */
    private String nombre;
    private  int puntos;
    private int ronda;
/**
 * constructor con parametros
 * @param nombre es un string
 * @param puntos es un int
 * @param ronda es un int
 */
    public jugador(String nombre, int puntos, int ronda) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ronda = ronda;
    }
/**
 * 
 * @return devuelve el nombre
 */
    public String getNombre() {
        return nombre;
    }
/**
 * 
 * @param nombre metodo para asignar valor a nombre
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * 
 * @return devuelve los puntos
 */
    public int getPuntos() {
        return puntos;
    }
/**
 * 
 * @param puntos  metodo para asignar valor a puntos
 */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
/**
 * 
 * @return devuelve la ronda
 */
    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }
    
    
}
