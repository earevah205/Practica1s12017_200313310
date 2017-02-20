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
    private NodoTablero nodoInicioTablero;
    private NodoTablero nodoFinTablero;
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

    /**
     * @return the nodoInicioTablero
     */
    public NodoTablero getNodoInicioTablero() {
        return nodoInicioTablero;
    }

    /**
     * @param nodoInicioTablero the nodoInicioTablero to set
     */
    public void setNodoInicioTablero(NodoTablero nodoInicioTablero) {
        this.nodoInicioTablero = nodoInicioTablero;
    }

    /**
     * @return the nodoFinTablero
     */
    public NodoTablero getNodoFinTablero() {
        return nodoFinTablero;
    }

    /**
     * @param nodoFinTablero the nodoFinTablero to set
     */
    public void setNodoFinTablero(NodoTablero nodoFinTablero) {
        this.nodoFinTablero = nodoFinTablero;
    }

}
