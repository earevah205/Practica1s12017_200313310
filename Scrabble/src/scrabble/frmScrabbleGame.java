/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import scrabble.edd.ColaDeFichas;
import scrabble.edd.ListaDiccionario;
import scrabble.edd.ListaCircularJugadores;
import scrabble.edd.Tablero;
import scrabble.edd_models.Ficha;
import scrabble.edd_models.Jugador;
import scrabble.edd_models.Letra;
import scrabble.xml_models.Scrabble;

/**
 *
 * @author estuardoarevalo
 */
public class frmScrabbleGame extends javax.swing.JFrame {

    private Scrabble scrabble;
    private ListaDiccionario diccionario = new ListaDiccionario();
    private Tablero tablero = new Tablero();
    private ColaDeFichas colaDeFichas = new ColaDeFichas();
    private ListaCircularJugadores listaJugadores = new ListaCircularJugadores();
    
    /**
     * Creates new form frmScrabbleGame
     */
    public frmScrabbleGame(frmStartup parent, Scrabble _scrabble) {
        initComponents();
        //----------------------------------------------------------------
        // Inicializando el JFrame
        //color de fondo
        this.getContentPane().setBackground(new Color(191, 30, 45));
        //colocar en el centro de la pantalla
        this.setLocationRelativeTo(null);
        //que no ocurra nada cuando lo cierran pues esto lo maneja frmStartup
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //----------------------------------------------------------------
        
        scrabble = _scrabble;
        
        //----------------------------------------------------------------
        //Lista Simple para el ListaDiccionario de Palabras
        //
        //inicializar el ListaDiccionario de Palabras
        String[] palabras_from_xml = scrabble.getDiccionario().getPalabras();
        for(int i = 0; i < palabras_from_xml.length; i++){
            diccionario.agregarAlInicio(palabras_from_xml[i]);
        }
        //----------------------------------------------------------------
        
        
        //----------------------------------------------------------------
        //Matriz ortogo
        //
        //llenar el tablero
        for (int x=0; x<scrabble.getDimension(); x++){
            for (int y=0; y<scrabble.getDimension(); y++){
                int multiplicador = 1;
                
                if (scrabble.getDobles().existe(x, y)) multiplicador = 2;
                if (scrabble.getTriples().existe(x, y)) multiplicador = 3;
                
                tablero.insertar(x, y, multiplicador);
            }
        }
        //----------------------------------------------------------------
        
        
        //----------------------------------------------------------------
        //creación de la fichas segun el enunciado
        //se utiliza una ArrayList solo con la finalidad de tener la cantidad 
        //total de fichas y su punteo, esto luego se distribuye de forma
        //random en la "ColaDeFichas"
        ArrayList<Letra> configuracionLetras = new ArrayList<>();
        configuracionLetras.add(new Letra("A",1,12));
        configuracionLetras.add(new Letra("E",1,12));
        configuracionLetras.add(new Letra("O",1,9));
        configuracionLetras.add(new Letra("I",1,6));
        configuracionLetras.add(new Letra("S",1,6));
        configuracionLetras.add(new Letra("N",1,5));
        configuracionLetras.add(new Letra("L",1,4));
        configuracionLetras.add(new Letra("R",1,5));
        configuracionLetras.add(new Letra("U",1,5));
        configuracionLetras.add(new Letra("T",1,4));
        configuracionLetras.add(new Letra("D",2,5));
        configuracionLetras.add(new Letra("G",2,2));
        configuracionLetras.add(new Letra("C",3,4));
        configuracionLetras.add(new Letra("B",3,2));
        configuracionLetras.add(new Letra("M",3,2));
        configuracionLetras.add(new Letra("P",3,2));
        configuracionLetras.add(new Letra("H",4,2));
        configuracionLetras.add(new Letra("F",4,1));
        configuracionLetras.add(new Letra("V",4,1));
        configuracionLetras.add(new Letra("Y",4,1));
        configuracionLetras.add(new Letra("Q",5,1));
        configuracionLetras.add(new Letra("J",8,1));
        configuracionLetras.add(new Letra("Ñ",8,1));
        configuracionLetras.add(new Letra("X",8,1));
        configuracionLetras.add(new Letra("Z",10,1));
        
        //ahora se ingresarán de forma aleatoria en la "ColaDeFichas"
        while (configuracionLetras.size() > 0){
            //vamos a vaciar la configuracion de letras de forma aleatoria
            
            Random rand = new Random();
            int n = 0;
            
            if (configuracionLetras.size() > 1){
                n = rand.nextInt(configuracionLetras.size()-1);
            }
            
            if (n < configuracionLetras.size()){
                Letra letra = configuracionLetras.get(n);
                
                //creamos la ficha para se utilizada en el tablero
                Ficha ficha = new Ficha();
                ficha.setLetra(letra.getLetra());
                ficha.setPuntos(letra.getPunteo());
                
                //encolamos
                colaDeFichas.encolar(ficha);
                
                //revisamos la cantidad que aún tenemos de estas letras
                if (letra.getCantidad()==1){
                    //remover del arreglo
                    configuracionLetras.remove(n);
                }else{
                    //actualizar del arreglo
                    configuracionLetras.get(n).setCantidad(letra.getCantidad()-1);
                }
                
            }
        }
        //----------------------------------------------------------------
        
        
        //----------------------------------------------------------------
        //Agregar los jugadores a la Lista Circular
        for (int i = 0; i < parent.getJugadores().size(); i++){
            Jugador jugador = new Jugador(parent.getJugadores().get(i).toString());
            listaJugadores.Insertar(jugador);
        }
        
        System.out.println("La cantidad de jugadores en la cola es de = " + parent.getJugadores().size());
        System.out.println("Fin de Carga");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
