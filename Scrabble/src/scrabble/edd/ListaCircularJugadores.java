/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import com.github.jabbalaci.graphviz.GraphViz;
import java.io.File;
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
    
    public String crearImagenGraphviz(){
        
        
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        
        
        NodoJugador nodo = nodoActual;
        if (nodo!=null){
            
            while(nodo.getSiguiente()!=nodoActual){
                
                String s = nodo.getJugador().getNombre();
                s += " -> " + nodo.getSiguiente().getJugador().getNombre();
                gv.addln(s);
                
                nodo = nodo.getSiguiente();
            }
            
            //una ultima vez para cerrar el ciclo
            String s = nodo.getJugador().getNombre();
            s += " -> " + nodo.getSiguiente().getJugador().getNombre();
            gv.addln(s);

            
            //gv.addln("A -> B;");
            //gv.addln("A -> C;");
            //gv.addln("B -> C;");
        }
        gv.addln(gv.end_graph());
	        
        System.out.println(gv.getDotSource());
        gv.increaseDpi();   // 106 dpi
        String type = "gif";
        String repesentationType= "dot";
        String imagePath = gv.getTempDir() + "/out"+gv.getImageDpi()+"."+ type;
	File out = new File( imagePath );  
	gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
        
        return imagePath;
        
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
