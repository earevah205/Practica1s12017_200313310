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
public class NodoLateralTablero {
    private NodoLateralTablero arriba;
    private NodoLateralTablero abajo;
    private NodoTablero nodoInicioTablero;
    private NodoTablero nodoFinTablero;
    private int posicionY;

    /**
     * @return the arriba
     */
    public NodoLateralTablero getArriba() {
        return arriba;
    }

    /**
     * @param arriba the arriba to set
     */
    public void setArriba(NodoLateralTablero arriba) {
        this.arriba = arriba;
    }

    /**
     * @return the abajo
     */
    public NodoLateralTablero getAbajo() {
        return abajo;
    }

    /**
     * @param abajo the abajo to set
     */
    public void setAbajo(NodoLateralTablero abajo) {
        this.abajo = abajo;
    }

    /**
     * @return the posicionY
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * @param posicionY the posicionY to set
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
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
