/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

/**
 *
 * @author estuardoarevalo
 */
public class NodoCabeceraTablero {
    private NodoCabeceraTablero izquierda;
    private NodoCabeceraTablero derecha;
    private int posicionX;

    /**
     * @return the izquierda
     */
    public NodoCabeceraTablero getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(NodoCabeceraTablero izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * @return the derecha
     */
    public NodoCabeceraTablero getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(NodoCabeceraTablero derecha) {
        this.derecha = derecha;
    }

    /**
     * @return the posicionX
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * @param posicionX the posicionX to set
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

}
