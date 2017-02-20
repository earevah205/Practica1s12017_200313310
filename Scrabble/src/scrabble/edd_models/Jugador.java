/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd_models;

import scrabble.edd.ListaFichasJugador;

/**
 *
 * @author estuardoarevalo
 */
public class Jugador {
    private String nombre;
    private ListaFichasJugador fichas = new ListaFichasJugador();
    private int punteo;
    
    public Jugador(String _nombre){
        nombre = _nombre;
    }

    
    public void agregarFicha(Ficha _ficha){
        fichas.agregarAlFinal(_ficha);
    }
    
    public void quitarFicha(Ficha _ficha){
        fichas.eliminarFicha(_ficha);
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the punteo
     */
    public int getPunteo() {
        return punteo;
    }

    /**
     * @param punteo the punteo to set
     */
    public void setPunteo(int punteo) {
        this.punteo = punteo;
    }
}
