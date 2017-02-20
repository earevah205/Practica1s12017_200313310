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
public class ListaLateralTablero {
    private NodoLateralTablero nodoInicio;
    private NodoLateralTablero nodoFin;

    
    
    
    /**
     * @return the nodoInicio
     */
    public NodoLateralTablero getNodoInicio() {
        return nodoInicio;
    }

    /**
     * @param nodoInicio the nodoInicio to set
     */
    public void setNodoInicio(NodoLateralTablero nodoInicio) {
        this.nodoInicio = nodoInicio;
    }

    /**
     * @return the nodoFin
     */
    public NodoLateralTablero getNodoFin() {
        return nodoFin;
    }

    /**
     * @param nodoFin the nodoFin to set
     */
    public void setNodoFin(NodoLateralTablero nodoFin) {
        this.nodoFin = nodoFin;
    }
}
