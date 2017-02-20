/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import scrabble.edd_models.Palabra;

/**
 *
 * @author estuardoarevalo
 */
public class NodoDiccionario {
    private NodoDiccionario siguiente;
    private Palabra palabra;

    /**
     * @return the siguiente
     */
    public NodoDiccionario getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoDiccionario siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the palabra
     */
    public Palabra getPalabra() {
        return palabra;
    }

    /**
     * @param palabra the palabra to set
     */
    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }
}
