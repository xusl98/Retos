/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import ficherosEjercicio1.Serializador;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author javier 
 * @version 1.1
 */
public class MiModelo extends AbstractTableModel {
/** 
  *  atributos de la clase modelo
  *   jugadores que es un array de jugador
  *  cabezera que es un array de string
  *  fichero que es un file
    */
    private List<jugador> jugadores;
    private String[] cabecera = {"Nombre", "Puntos", "Ronda"};
    private File fichero = new File("personas.dat");
/**  
    constructor con parametro y metodo
    jugadores es un array
    metodo leer
    */
    public MiModelo() {
        this.jugadores = new ArrayList();
        Serializador.leer(fichero);
    }
/**
 * metodo que devuelve el tamaÃ±o de jugadores
 */
    @Override
    public int getRowCount() {
        return jugadores.size();
    }
/**
 * metodo que nos devuelve la longitud de la cabecera 
 */
    @Override
    public int getColumnCount() {
        return cabecera.length;
    }
/**
 * metodo que devuelve el contenido de la cabevera
 */
    @Override
    public String getColumnName(int i) {
        return cabecera[i]; //To change body of generated methods, choose Tools | Templates.
    }
/**
 * metodo que mete datos a la tabla 
 */
    @Override
    public Object getValueAt(int i, int i1) {
        //Recorrer todas las filas de la lista
        jugador jugadores = this.jugadores.get(i);
        //SegÃºn el numero de columna mostrar un dato determinado
        switch (i1) {
            case 0:
                return jugadores.getNombre();
            case 1:
                return jugadores.getPuntos();
            case 2:
                return jugadores.getRonda();
            
        }
        return null;
    }
/**
 * metodo aniadir que aÃ±ade a jugadores una persona y despues a la tabla
 * @param per es un jugador
    */
    public void aniadir(jugador per) {
        this.jugadores.add(per);
        //Confirmar los cambios de la tabla
        fireTableDataChanged();
    }
/**
 * metodo borrar borra una persona de jugadores y de la tabla 
 * @param i es un int
 */
    public void borrar(int i) {
        jugadores.remove(i);
        //Confirmar los cambios de la tabla
        fireTableDataChanged();
    }



/**
 * metodo leer que lee un fichero y lo mete a jugadores mas a la tabla 
 */

    public void leer() {
        if (fichero.exists()) {
            List<jugador> personasLeidas = Serializador.leer(fichero);
            jugadores.addAll(personasLeidas);
            fireTableDataChanged();
        }
    }
/**
 * metodo grabar que graba jugadores en un fichero  
 */
    public void grabar() {
        Serializador.grabar(fichero, jugadores);
    }

}
