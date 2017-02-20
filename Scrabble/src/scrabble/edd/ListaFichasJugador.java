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
public class ListaFichasJugador {
    private NodoFicha nodoInicio;
    private NodoFicha nodoFin;
    
    public NodoFicha agregarAlInicio(Ficha _ficha){
        
        NodoFicha nuevo = new NodoFicha();
        nuevo.setFicha(_ficha);
        
        if (nodoInicio==null){
            nodoInicio = nodoFin = nuevo;
        }else{
            nuevo.setSiguiente(nodoInicio);
            nodoInicio = nuevo;
        }
        return nuevo;
    }
    
    public NodoFicha agregarAlFinal(Ficha _ficha){
        
        NodoFicha nuevo = new NodoFicha();
        nuevo.setFicha(_ficha);
        
        if (nodoFin==null){
            nodoInicio = nodoFin = nuevo;
        }else{
            nodoFin.setSiguiente(nuevo);
            nodoFin = nuevo;
        }
        return nuevo;
    }
    
    public void eliminarFicha(Ficha _ficha){
        
        if (nodoInicio==null) return;
        
        NodoFicha temp = nodoInicio;
        NodoFicha anterior = null;
        
        //recorremos la lista
        while (temp != null){
            
            // si el nodo contiene la palabra que estamos buscando
            // entonces lo eliminamos
            if (temp.getFicha().getLetra().compareToIgnoreCase(_ficha.getLetra())==0){

                //es el inicio de la lista
                if (anterior == null){ 
                    nodoInicio = temp.getSiguiente();
                }else{
                    anterior.setSiguiente(temp.getSiguiente());
                }
                
                // para que salga del ciclo
                temp = null;
                
            }else{
                anterior = temp;
                temp = temp.getSiguiente();
            }
        }
        
    }
    
    public NodoFicha getInicio(){
        return nodoInicio;
    }
    
    public NodoFicha getFinal(){
        return nodoFin;
    }
    
}
