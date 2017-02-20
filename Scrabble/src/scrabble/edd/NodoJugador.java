/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import scrabble.edd_models.Jugador;

/**
 *
 * @author estuardoarevalo
 */
public class NodoJugador {
    private NodoJugador siguiente;
    private Jugador jugador;

    /**
     * @return the siguiente
     */
    public NodoJugador getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoJugador siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * @param jugador the jugador to set
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
