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
    private NodoDiccionario mHead;
    
    public NodoDiccionario agregarAlInicio(String _palabra){
        
        NodoDiccionario nuevo = new NodoDiccionario();
        nuevo.setPalabra(new Palabra(_palabra));
        
        if (mHead==null){
            mHead = nuevo;
        }else{
            mHead.setSiguiente(nuevo);
            mHead = nuevo;
        }
        return nuevo;
    }
    
}
