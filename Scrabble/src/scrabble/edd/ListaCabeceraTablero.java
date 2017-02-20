/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import scrabble.edd.NodoCabeceraTablero;

/**
 *
 * @author estuardoarevalo
 */
public class ListaCabeceraTablero {
    private NodoCabeceraTablero nodoInicio;
    private NodoCabeceraTablero nodoFin;

    public NodoCabeceraTablero insertar(int posicionX) {
        
        NodoCabeceraTablero nuevo = new NodoCabeceraTablero();
        nuevo.setPosicionX(posicionX);
        
        if (nodoInicio==null){
            nodoInicio = nodoFin = nuevo;
        } else {
            if (posicionX < nodoInicio.getPosicionX()) {
                insertarAlInicio(nuevo);
            } else if (posicionX > nodoFin.getPosicionX()) {
                insertarAlFinal(nuevo);
            } else {
                insertarAlMedio(nuevo);
            }
        }
        
        return nuevo;
    }
    
    private void insertarAlInicio(NodoCabeceraTablero nuevo){
        
        nuevo.setDerecha(nodoInicio);
        nodoInicio.setIzquierda(nuevo);
        nodoInicio = nuevo;

    }
    
    private void insertarAlFinal(NodoCabeceraTablero nuevo){
        
        nodoFin.setDerecha(nuevo);
        nuevo.setIzquierda(nodoFin);
        nodoFin = nuevo;
       
    }
    
    private void insertarAlMedio(NodoCabeceraTablero nuevo){
        
        NodoCabeceraTablero temp = nodoInicio;
        
        while (temp.getPosicionX() < nuevo.getPosicionX()) {
            temp = temp.getDerecha();
        }
        
        NodoCabeceraTablero temp2 = temp.getIzquierda();
        temp2.setDerecha(nuevo);
        nuevo.setDerecha(temp);
        nuevo.setIzquierda(temp2);
        temp.setIzquierda(nuevo);
        
    }
    
    public NodoCabeceraTablero buscar(int positionX) { 
        
        NodoCabeceraTablero nodo = nodoInicio;
        while (nodo != null) {
            if (nodo.getPosicionX() == positionX) return nodo;
            nodo = nodo.getDerecha();
        }
        
        return null;
    }
    
    /**
     * @return the nodoInicio
     */
    public NodoCabeceraTablero getNodoInicio() {
        return nodoInicio;
    }

    /**
     * @param nodoInicio the nodoInicio to set
     */
    public void setNodoInicio(NodoCabeceraTablero nodoInicio) {
        this.nodoInicio = nodoInicio;
    }

    /**
     * @return the nodoFin
     */
    public NodoCabeceraTablero getNodoFin() {
        return nodoFin;
    }

    /**
     * @param nodoFin the nodoFin to set
     */
    public void setNodoFin(NodoCabeceraTablero nodoFin) {
        this.nodoFin = nodoFin;
    }

    
}
