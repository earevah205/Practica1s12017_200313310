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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import scrabble.edd.ColaDeFichas;
import scrabble.edd.ListaCabeceraTablero;
import scrabble.edd.ListaDiccionario;
import scrabble.edd.ListaCircularJugadores;
import scrabble.edd.ListaFichasJugador;
import scrabble.edd.NodoCabeceraTablero;
import scrabble.edd.NodoFicha;
import scrabble.edd.NodoTablero;
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
    public static final int FICHAS_JUGADOR_OFFSET_Y = 80;
    public static final int TABLERO_OFFSET = 32;
    public static final Dimension FICHA_SIZE = new Dimension(32, 32);
        
        
    
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
        mostrarFichasJugador();
        //Mostrar el tablero de juego
        mostrarTablero();
                
        
        
        
        System.out.println("La cantidad de jugadores en la cola es de = " + parent.getJugadores().size());
        System.out.println("Fin de Carga");
        
    }

    private void mostrarTablero(){
        
        Insets insets = panelTablero.getInsets();
        
        
        if (tablero.getListaCabecera().getNodoInicio()!=null){
            
            NodoCabeceraTablero cabecera =  tablero.getListaCabecera().getNodoInicio();
            
            while (cabecera != null){
            
                int x = cabecera.getPosicionX();

                if (cabecera.getNodoInicioTablero()!=null){
                    NodoTablero celda = cabecera.getNodoInicioTablero();

                    while (celda != null){
                        
                        Ficha ficha = celda.getFicha();
                        int multiplicador = celda.getMultiplicador();
                        int y = celda.getLateral().getPosicionY();

                        //crear label
                        JLabel lbl = new javax.swing.JLabel();
                        lbl.setIcon(new ImageIcon(getClass().getResource("/scrabble/images/empty.png")));
                        lbl.setPreferredSize(FICHA_SIZE);
                        lbl.setBounds((TABLERO_OFFSET * x) + insets.left, (TABLERO_OFFSET * y) + insets.top,
                                        FICHA_SIZE.width, FICHA_SIZE.height);

                        celda.setLabel(lbl);
                        panelTablero.add(lbl);
                        
                        celda = celda.getAbajo();
                    }
                }
                cabecera = cabecera.getDerecha();
            }
            
        }
        
        
        //recorremos la cabecera y por cada nodo recorremos la lista asociada
        
        
        
    }
    private void mostrarFichasJugador( ){
    
        
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
            lbl.setIcon(new ImageIcon(getClass().getResource("/scrabble/images/" + ficha.getLetra() + ".png")));
            lbl.setPreferredSize(FICHA_SIZE);
            lbl.setBounds((FICHAS_JUGADOR_OFFSET_X * numero) + insets.left, scrabble.getDimension() * TABLERO_OFFSET + FICHAS_JUGADOR_OFFSET_Y + insets.top,
                            FICHA_SIZE.width, FICHA_SIZE.height);
            
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTablero.setOpaque(false);
        panelTablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout panelEstadisticoLayout = new javax.swing.GroupLayout(panelEstadistico);
        panelEstadistico.setLayout(panelEstadisticoLayout);
        panelEstadisticoLayout.setHorizontalGroup(
            panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadisticoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 380, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        panelEstadisticoLayout.setVerticalGroup(
            panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadisticoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelEstadistico;
    private javax.swing.JPanel panelTablero;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentResized(ComponentEvent e) {
        this.panelTablero.setBounds(0, 0, scrabble.getDimension()*TABLERO_OFFSET, this.getHeight());
        
        this.panelEstadistico.setBounds(scrabble.getDimension()*TABLERO_OFFSET+1,0,this.getWidth()-scrabble.getDimension()*TABLERO_OFFSET+1,this.getHeight());
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
