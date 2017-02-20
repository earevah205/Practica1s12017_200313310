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
public class NodoTablero {
    private NodoTablero arriba;
    private NodoTablero abajo;
    private NodoTablero izquierda;
    private NodoTablero derecha;
    private Ficha ficha;
    private int multiplicador = 1;

    /**
     * @return the arriba
     */
    public NodoTablero getArriba() {
        return arriba;
    }

    /**
     * @param arriba the arriba to set
     */
    public void setArriba(NodoTablero arriba) {
        this.arriba = arriba;
    }

    /**
     * @return the abajo
     */
    public NodoTablero getAbajo() {
        return abajo;
    }

    /**
     * @param abajo the abajo to set
     */
    public void setAbajo(NodoTablero abajo) {
        this.abajo = abajo;
    }

    /**
     * @return the izquierda
     */
    public NodoTablero getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(NodoTablero izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * @return the derecha
     */
    public NodoTablero getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(NodoTablero derecha) {
        this.derecha = derecha;
    }

    /**
     * @return the multiplicador
     */
    public int getMultiplicador() {
        return multiplicador;
    }

    /**
     * @param multiplicador the multiplicador to set
     */
    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }

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
}
