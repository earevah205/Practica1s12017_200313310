/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import scrabble.edd_models.Ficha;

/**
 *
 * @author estuardoarevalo
 */
public class NodoFicha {
    private Ficha ficha;
    private NodoFicha siguiente;

    /**
     * @return the ficha
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * @param ficha the ficha to set
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * @return the siguiente
     */
    public NodoFicha getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoFicha siguiente) {
        this.siguiente = siguiente;
    }
}
