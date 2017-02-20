/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

/**
 *
 * @author estuardoarevalo
 * Matriz Ortogonal (Matriz Dispersa)
 */
public class Tablero {
    
    ListaCabeceraTablero listaCabecera = new ListaCabeceraTablero();
    ListaLateralTablero listaLateral = new ListaLateralTablero();
    
    public NodoTablero insertar(int x, int y, int multiplicador){
        NodoTablero nuevo = new NodoTablero();
        nuevo.setMultiplicador(multiplicador);
        
        NodoCabeceraTablero cabecera = listaCabecera.buscar(x);
        if (cabecera==null) cabecera = listaCabecera.insertar(x);
        
        NodoLateralTablero lateral = listaLateral.buscar(y);
        if (lateral==null) lateral = listaLateral.insertar(y);
        
        //si la cabecera aún no tiene el inicio del tablero
        //es porque esta vacio
        if (cabecera.getNodoInicioTablero()==null) 
            cabecera.setNodoInicioTablero(nuevo);
        
        //si la cabecera aún no tiene el inicio del tablero
        //es porque esta vacio
        if (lateral.getNodoInicioTablero()==null) 
            lateral.setNodoInicioTablero(nuevo);
        
        
        //ahora con la cabecera y con lateral vamos a movernos
        
        
        return nuevo;
    }
    
    //public NodoTablero agregarNodo(int x, int y){
        
        
    //}
    
}
