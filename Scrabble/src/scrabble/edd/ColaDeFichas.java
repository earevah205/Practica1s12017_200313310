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
public class ColaDeFichas {
    private NodoFicha nodoInicio;
    private NodoFicha nodoFin;
    private int tamano;
    
    public Ficha top() {
  
        if( nodoInicio == null ) {
          return null;
        }

        return nodoInicio.getFicha();
    }

    public Ficha desencolar( ) {
    
        if( nodoInicio == null ) {
          return null;
        }

        Ficha ficha = nodoInicio.getFicha();

        if( nodoInicio.getSiguiente() == null ) {
          nodoInicio = null;
          nodoFin = null;
        } else {
          nodoInicio = nodoInicio.getSiguiente();
        }

        tamano--;
        return ficha;
      }

      public void encolar( Ficha ficha )
      {

        NodoFicha nuevo = new NodoFicha();
        nuevo.setFicha(ficha);
        
        if( nodoFin == null ) {
          nodoInicio = nuevo;
          nodoFin = nuevo;      
        } else {
          nodoFin.setSiguiente(nuevo);
          nodoFin = nuevo;
        }

        tamano++;
      }
      
      public int getTamano(){
          return tamano;
      }
}
