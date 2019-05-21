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
 * @author dam106
 */
public class MiModelo extends AbstractTableModel {

    private List<jugador> jugadores;
    private String[] cabecera = {"Nombre", "Puntos", "Ronda"};
    private File fichero = new File("personas.dat");

    public MiModelo() {
        this.jugadores = new ArrayList();
        Serializador.leer(fichero);
    }

    @Override
    public int getRowCount() {
        return jugadores.size();
    }

    @Override
    public int getColumnCount() {
        return cabecera.length;
    }

    @Override
    public String getColumnName(int i) {
        return cabecera[i]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int i, int i1) {
        //Recorrer todas las filas de la lista
        jugador jugadores = this.jugadores.get(i);
        //Según el numero de columna mostrar un dato determinado
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

    public void aniadir(jugador per) {
        this.jugadores.add(per);
        //Confirmar los cambios de la tabla
        fireTableDataChanged();
    }

    public void borrar(int i) {
        jugadores.remove(i);
        //Confirmar los cambios de la tabla
        fireTableDataChanged();
    }





    public void leer() {
        if (fichero.exists()) {
            List<jugador> personasLeidas = Serializador.leer(fichero);
            jugadores.addAll(personasLeidas);
            fireTableDataChanged();
        }
    }

    public void grabar() {
        Serializador.grabar(fichero, jugadores);
    }

}
