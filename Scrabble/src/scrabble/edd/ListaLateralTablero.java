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

    public NodoLateralTablero insertar(int posicionY) {
        
        NodoLateralTablero nuevo = new NodoLateralTablero();
        nuevo.setPosicionY(posicionY);
        
        if (nodoInicio==null){
            nodoInicio = nodoFin = nuevo;
        } else {
            if (posicionY < nodoInicio.getPosicionY()) {
                insertarAlInicio(nuevo);
            } else if (posicionY > nodoFin.getPosicionY()) {
                insertarAlFinal(nuevo);
            } else {
                insertarAlMedio(nuevo);
            }
        }
        
        return nuevo;
    }
    
    private void insertarAlInicio(NodoLateralTablero nuevo){
        
        nuevo.setAbajo(nodoInicio);
        nodoInicio.setArriba(nuevo);
        nodoInicio = nuevo;

    }
    
    private void insertarAlFinal(NodoLateralTablero nuevo){
        
        nodoFin.setAbajo(nuevo);
        nuevo.setArriba(nodoFin);
        nodoFin = nuevo;
       
    }
    
    private void insertarAlMedio(NodoLateralTablero nuevo){
        
        NodoLateralTablero temp = nodoInicio;
        
        while (temp.getPosicionY() < nuevo.getPosicionY()) {
            temp = temp.getAbajo();
        }
        
        NodoLateralTablero temp2 = temp.getArriba();
        temp2.setAbajo(nuevo);
        nuevo.setAbajo(temp);
        nuevo.setArriba(temp2);
        temp.setArriba(nuevo);
        
    }
    
    public NodoLateralTablero buscar(int positionY) { 
        
        NodoLateralTablero nodo = nodoInicio;
        while (nodo != null) {
            if (nodo.getPosicionY() == positionY) return nodo;
            nodo = nodo.getAbajo();
        }
        
        return null;
    }
    
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
