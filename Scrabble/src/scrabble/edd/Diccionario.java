/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import scrabble.edd_models.Palabra;

/**
 *
 * @author estuardoarevalo
 */
public class Diccionario {
    private NodoDiccionario nodoInicio;
    private NodoDiccionario nodoFin;
    
    public NodoDiccionario agregarAlInicio(String _palabra){
        
        NodoDiccionario nuevo = new NodoDiccionario();
        nuevo.setPalabra(new Palabra(_palabra));
        
        if (nodoInicio==null){
            nodoInicio = nodoFin = nuevo;
        }else{
            nuevo.setSiguiente(nodoInicio);
            nodoInicio = nuevo;
        }
        return nuevo;
    }
    
    public NodoDiccionario agregarAlFinal(String _palabra){
        
        NodoDiccionario nuevo = new NodoDiccionario();
        nuevo.setPalabra(new Palabra(_palabra));
        
        if (nodoFin==null){
            nodoInicio = nodoFin = nuevo;
        }else{
            nodoFin.setSiguiente(nuevo);
            nodoFin = nuevo;
        }
        return nuevo;
    }
    
    public void eliminarPalabra(String _palabra){
        
        if (nodoInicio==null) return;
        
        NodoDiccionario temp = nodoInicio;
        NodoDiccionario anterior = null;
        //recorremos la lista
        
        while (temp != null){
            
            // si el nodo contiene la palabra que estamos buscando
            // entonces lo eliminamos
            if (temp.getPalabra().get().compareToIgnoreCase(_palabra)==0){

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
    
    public NodoDiccionario getInicio(){
        return nodoInicio;
    }
    
    public NodoDiccionario getFinal(){
        return nodoFin;
    }
    
    
}
