/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd;

import com.github.jabbalaci.graphviz.GraphViz;
import java.io.File;
import scrabble.edd_models.Palabra;

/**
 *
 * @author estuardoarevalo
 */
public class ListaDiccionario {
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
    
    
    public String crearImagenGraphviz(){
        
        
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        
        
        NodoDiccionario nodo = nodoInicio;
        int x = 1;    
        while(nodo!=null){
            if (nodo.getSiguiente()!=null){
                String s = "{" + nodo.getPalabra().get().toLowerCase() + x + " [label=\"" + nodo.getPalabra().get() + "\"] }";
                s += " -> {" + nodo.getSiguiente().getPalabra().get().toLowerCase() + (x+1) + " [label=\"" + nodo.getSiguiente().getPalabra().get() + "\"] }";
                gv.addln(s);
            }else{
                String s = "{" + nodo.getPalabra().get().toLowerCase() + x + " [label=\"" + nodo.getPalabra().get() + "\"] }";
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
        String imagePath = gv.getTempDir() + "/diccionario"+gv.getImageDpi()+"."+ type;
	File out = new File( imagePath );  
	gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
        
        return imagePath;
        
    }
    
    
}
