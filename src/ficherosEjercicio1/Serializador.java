/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherosEjercicio1;

import juego.jugador;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javie
 */
public class Serializador {

    public static List<jugador> leer(File f) {
        List<jugador> jugadores = new ArrayList<>();

        ObjectInputStream fi = null;
        jugador jugador;
        try {
            fi = new ObjectInputStream(new FileInputStream(f));
            do {
                jugador = (jugador) fi.readObject();
                jugadores.add(jugador);
            } while (true);
        } catch (EOFException ex) {
        } catch (IOException e) {
            System.err.println("error al leer");
        } catch (ClassNotFoundException en) {
            System.err.println(en.toString());
        }
        try {
            if (fi != null) {
                fi.close();
            }
        } catch (IOException ex) {
        }
        return jugadores;
    }

    public static void grabar(File f, List<jugador> jugadores) {
        try {
            ObjectOutputStream fo = new ObjectOutputStream(new FileOutputStream(f, false));

            for (jugador p : jugadores) {
                fo.writeObject(p);
            }

            fo.close();
        } catch (IOException e) {
            System.err.println("error al acceder");
        }
    }
}
