package cosas;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daw120
 */
public class RankingCoches implements Serializable, Comparable<RankingCoches>{
    
    private String nombre;
    private int aciertos;
    private int fallos;

    public RankingCoches(String nombre, int aciertos, int fallos) {
        this.nombre = nombre;
        this.aciertos = aciertos;
        this.fallos = fallos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    @Override
    public int compareTo(RankingCoches t) {
        return aciertos - t.aciertos;
    }
    
    
    
    
    
}
