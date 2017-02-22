/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import com.github.jabbalaci.graphviz.GraphViz;
import java.io.File;

/**
 *
 * @author estuardoarevalo
 * Matriz Ortogonal (Matriz Dispersa)
 */
public class Tablero {
    
    ListaCabeceraTablero listaCabecera = new ListaCabeceraTablero();
    ListaLateralTablero listaLateral = new ListaLateralTablero();
    
    public ListaCabeceraTablero getListaCabecera(){
        return listaCabecera;
    }
    
    
    public NodoTablero insertar(int x, int y, int multiplicador){
        NodoTablero nuevo = new NodoTablero();
        nuevo.setMultiplicador(multiplicador);
        
        NodoCabeceraTablero cabecera = listaCabecera.buscar(x);
        if (cabecera==null) cabecera = listaCabecera.insertar(x);
        nuevo.setCabecera(cabecera);
        
        NodoLateralTablero lateral = listaLateral.buscar(y);
        if (lateral==null) lateral = listaLateral.insertar(y);
        nuevo.setLateral(lateral);
        
        //si la cabecera aún no tiene el inicio del tablero
        //es porque esta vacio
        if (cabecera.getNodoInicioTablero()==null){ 
            cabecera.setNodoInicioTablero(nuevo);
            cabecera.setNodoFinTablero(nuevo);
        }else{
            if (y < cabecera.getNodoInicioTablero().getLateral().getPosicionY()) {
                insertarAlInicioY(cabecera.getNodoInicioTablero(), nuevo);
                cabecera.setNodoInicioTablero(nuevo);
            } else if (y > cabecera.getNodoFinTablero().getLateral().getPosicionY()) {
                insertarAlFinalY(cabecera.getNodoFinTablero(), nuevo);
                cabecera.setNodoFinTablero(nuevo);
            } else {
                insertarAlMedioY(cabecera.getNodoInicioTablero(), nuevo);
            } 
        }
        
        //si la cabecera aún no tiene el inicio del tablero
        //es porque esta vacio
        if (lateral.getNodoInicioTablero()==null) {
            lateral.setNodoInicioTablero(nuevo);
            lateral.setNodoFinTablero(nuevo);
        }else{
            if (x < lateral.getNodoInicioTablero().getCabecera().getPosicionX()) {
                insertarAlInicioX(lateral.getNodoInicioTablero(), nuevo);
                lateral.setNodoInicioTablero(nuevo);
            } else if (x > lateral.getNodoFinTablero().getCabecera().getPosicionX()) {
                insertarAlFinalX(lateral.getNodoFinTablero(), nuevo);
                lateral.setNodoFinTablero(nuevo);
            } else {
                insertarAlMedioX(lateral.getNodoInicioTablero(), nuevo);
            } 
        }
        
        return nuevo;
    }
    
    private void insertarAlInicioY(NodoTablero nodoInicio, NodoTablero nuevo){
        
        nuevo.setAbajo(nodoInicio);
        nodoInicio.setArriba(nuevo);
        nodoInicio = nuevo;

    }
    
    private void insertarAlFinalY(NodoTablero nodoFin, NodoTablero nuevo){
        
        nodoFin.setAbajo(nuevo);
        nuevo.setArriba(nodoFin);
        nodoFin = nuevo;
       
    }
    
    private void insertarAlMedioY(NodoTablero nodoInicio, NodoTablero nuevo){
        
        NodoTablero temp = nodoInicio;
        
        while (temp.getLateral().getPosicionY() < nuevo.getLateral().getPosicionY()) {
            temp = temp.getAbajo();
        }
        
        NodoTablero temp2 = temp.getArriba();
        temp2.setAbajo(nuevo);
        nuevo.setAbajo(temp);
        nuevo.setArriba(temp2);
        temp.setArriba(nuevo);
        
    }
    
    private void insertarAlInicioX(NodoTablero nodoInicio, NodoTablero nuevo){
        
        nuevo.setDerecha(nodoInicio);
        nodoInicio.setIzquierda(nuevo);
        nodoInicio = nuevo;

    }
    
    private void insertarAlFinalX(NodoTablero nodoFin, NodoTablero nuevo){
        
        nodoFin.setDerecha(nuevo);
        nuevo.setIzquierda(nodoFin);
        nodoFin = nuevo;
       
    }
    
    private void insertarAlMedioX(NodoTablero nodoInicio, NodoTablero nuevo){
        
        NodoTablero temp = nodoInicio;
        
        while (temp.getCabecera().getPosicionX() < nuevo.getCabecera().getPosicionX()) {
            temp = temp.getDerecha();
        }
        
        NodoTablero temp2 = temp.getIzquierda();
        temp2.setDerecha(nuevo);
        nuevo.setDerecha(temp);
        nuevo.setIzquierda(temp2);
        temp.setIzquierda(nuevo);
        
    }
    
    public NodoTablero obtenerNodo(int x, int y){
        
        NodoTablero nodoResultante = null;
        
        if (listaCabecera.buscar(x)!=null){
            
            NodoTablero nodo = listaCabecera.buscar(x).getNodoInicioTablero();
            
            while (nodo!=null){
                
                if (nodo.getLateral().getPosicionY()==y){
                    nodoResultante = nodo;
                    nodo = null;
                }else{
                    nodo = nodo.getAbajo();
                }
            }
            
            
        }
        
        if (nodoResultante!=null) return nodoResultante;
        
        return null;
    }
    
    public String crearImagenGraphviz(int dimensiones){
        
        
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        
        //b [shape=record label="{1|4|7}|{2|500|8}|{3|6|9}"];
        
        String s = "b [shape=record label=\"";
        
        for (int x=0; x<dimensiones; x++){
            s += "{";
            boolean first = true;
            for (int y=0; y<dimensiones; y++){
                
                if (!first) s += "|";
                    
                NodoTablero nodo = obtenerNodo(x,y);
                if (nodo.getFicha()==null){
                    s += "0";
                }else{
                    s += nodo.getFicha().getLetra();
                }
                
                first = false;
                
            }
            s += "}";
        }
        
        s += "\"];";
        
        gv.addln(s);
        
         
        gv.addln(gv.end_graph());
	        
        System.out.println(gv.getDotSource());
        gv.decreaseDpi();   // 106 dpi
        String type = "gif";
        String repesentationType= "dot";
        String imagePath = gv.getTempDir() + "/tablero"+gv.getImageDpi()+"."+ type;
	File out = new File( imagePath );  
	gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
        
        return imagePath;
        
    }
    
    
}
