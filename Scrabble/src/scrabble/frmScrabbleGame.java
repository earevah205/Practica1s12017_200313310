/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import scrabble.edd.ColaDeFichas;
import scrabble.edd.ListaDiccionario;
import scrabble.edd.ListaCircularJugadores;
import scrabble.edd.ListaFichasJugador;
import scrabble.edd.NodoFicha;
import scrabble.edd.Tablero;
import scrabble.edd_models.Ficha;
import scrabble.edd_models.Jugador;
import scrabble.edd_models.Letra;
import scrabble.xml_models.Scrabble;

/**
 *
 * @author estuardoarevalo
 */
public class frmScrabbleGame extends javax.swing.JFrame implements ComponentListener {

    public static final int FICHAS_JUGADOR_OFFSET_X = 40;
    public static final int FICHAS_JUGADOR_OFFSET_Y = -140;
        
    
    private Scrabble scrabble;
    private ListaDiccionario diccionario = new ListaDiccionario();
    private Tablero tablero = new Tablero();
    private ColaDeFichas colaDeFichas = new ColaDeFichas();
    private ListaCircularJugadores listaJugadores = new ListaCircularJugadores();
    private Jugador jugadorActual;
    
    private frmStartup parent;
    private ListenerDnD listenerDnD;
 
    
    /**
     * Creates new form frmScrabbleGame
     */
    public frmScrabbleGame(frmStartup _parent, Scrabble _scrabble) {
        initComponents();
        
        this.scrabble = _scrabble;
        this.parent = _parent;
        
        //----------------------------------------------------------------
        // Inicializando el JFrame
        //color de fondo
        this.getContentPane().setBackground(new Color(191, 30, 45));
        //colocar en el centro de la pantalla
        this.setLocationRelativeTo(null);
        //que no ocurra nada cuando lo cierran pues esto lo maneja frmStartup
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //maximizar
        this.addComponentListener(this);
        this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        this.setLayout(null);
        this.panelTablero.setLayout(null);
        
        
        listenerDnD = new ListenerDnD(this);
        this.addMouseListener(listenerDnD);
        this.addMouseMotionListener(listenerDnD);
        
        //----------------------------------------------------------------
        
        
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
            
            //Repartir 7 Fichas
            for (int j = 1; j <= 7; j++){
                jugador.agregarFicha(colaDeFichas.desencolar());
            }
            
            listaJugadores.Insertar(jugador);
        }
        
        
        
        //Mostrar la fichas del jugador actual
        mostraFichasJugador();
                
        
        
        
        System.out.println("La cantidad de jugadores en la cola es de = " + parent.getJugadores().size());
        System.out.println("Fin de Carga");
        
    }

    
    private void mostraFichasJugador( ){
    
        
        jugadorActual = listaJugadores.getNodoActual().getJugador();
        listenerDnD.setJugador(jugadorActual);
    
        Insets insets = panelTablero.getInsets();
        
        //Recorrer la lista de fichas
        ListaFichasJugador fichasJugador = jugadorActual.getFichas();
        NodoFicha nodo = fichasJugador.getInicio();
        int numero = 0;
        while (nodo!=null){
            numero++;
            Ficha ficha = nodo.getFicha();
            JLabel lbl = new javax.swing.JLabel();
            lbl.setIcon(ficha.getImageIcon());
            Dimension size = new Dimension(32, 32);
            lbl.setPreferredSize(size);
            lbl.setBounds((FICHAS_JUGADOR_OFFSET_X * numero) + insets.left, this.getHeight() + FICHAS_JUGADOR_OFFSET_Y + insets.top,
            size.width, size.height);
            ficha.setLabel(lbl);
            panelTablero.add(lbl);
            
            //movernos a la siguiente ficha
            nodo = nodo.getSiguiente();
        }       
        
        //this.getContentPane().revalidate();
        //this.getContentPane().repaint();
        //this.pack();
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTablero = new javax.swing.JPanel();
        panelEstadistico = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTablero.setOpaque(false);
        panelTablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panelEstadisticoLayout = new javax.swing.GroupLayout(panelEstadistico);
        panelEstadistico.setLayout(panelEstadisticoLayout);
        panelEstadisticoLayout.setHorizontalGroup(
            panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        panelEstadisticoLayout.setVerticalGroup(
            panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEstadistico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelEstadistico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelEstadistico;
    private javax.swing.JPanel panelTablero;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentResized(ComponentEvent e) {
        this.panelTablero.setBounds(0, 0, scrabble.getDimension()*40, this.getHeight());
        
        this.panelEstadistico.setBounds(scrabble.getDimension()*40+1,0,this.getWidth()-scrabble.getDimension()*40+1,this.getHeight());
        this.panelEstadistico.setBackground(Color.white);
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }
}
