/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import com.github.jabbalaci.graphviz.GraphViz;
import java.io.File;
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
      
      public String crearImagenGraphviz(){
        
        
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        
        
        NodoFicha nodo = nodoInicio;
        int x = 1;    
        while(nodo!=null){
            if (nodo.getSiguiente()!=null){
                String s = "{" + nodo.getFicha().getLetra().toLowerCase() + x + " [label=\"" + nodo.getFicha().getLetra() + "\"] }";
                s += " -> {" + nodo.getSiguiente().getFicha().getLetra().toLowerCase() + (x+1) + " [label=\"" + nodo.getSiguiente().getFicha().getLetra() + "\"] }";
                gv.addln(s);
            }else{
                String s = "{" + nodo.getFicha().getLetra().toLowerCase() + x + " [label=\"" + nodo.getFicha().getLetra() + "\"] }";
                gv.addln(s);
            }
            x++;
            nodo = nodo.getSiguiente();
        }
         
        gv.addln(gv.end_graph());
	        
        System.out.println(gv.getDotSource());
        gv.decreaseDpi();   // 106 dpi
        String type = "gif";
        String repesentationType= "dot";
        String imagePath = gv.getTempDir() + "/cola"+gv.getImageDpi()+"."+ type;
	File out = new File( imagePath );  
	gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
        
        return imagePath;
        
    }
    
}
