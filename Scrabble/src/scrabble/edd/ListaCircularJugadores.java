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
public class ListaCircularJugadores {
    private NodoJugador nodoActual;
    
    public void Insertar(Jugador jugador){
        NodoJugador nuevo = new NodoJugador();
        nuevo.setJugador(jugador);
        
        if(nodoActual == null) nodoActual = nuevo;
        else nuevo.setSiguiente(nodoActual.getSiguiente());
        
        // Para hacerla circular 
        nodoActual.setSiguiente(nuevo);
    }
    
    public boolean estaVacia() { return nodoActual == null; } 
    
    
    public void moverAlSiguiente() {
       if(nodoActual!=null) nodoActual = nodoActual.getSiguiente();
    }
    
    /**
     * @return the nodoActual
     */
    public NodoJugador getNodoActual() {
        return nodoActual;
    }

    /**
     * @param nodoActual the nodoActual to set
     */
    public void setNodoActual(NodoJugador nodoActual) {
        this.nodoActual = nodoActual;
    }
    
}
