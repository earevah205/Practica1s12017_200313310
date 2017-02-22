/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import com.github.jabbalaci.graphviz.GraphViz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import scrabble.edd.ColaDeFichas;
import scrabble.edd.ListaCabeceraTablero;
import scrabble.edd.ListaDiccionario;
import scrabble.edd.ListaCircularJugadores;
import scrabble.edd.ListaFichasJugador;
import scrabble.edd.NodoCabeceraTablero;
import scrabble.edd.NodoDiccionario;
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
        
        //this.panelTablero.isOptimizedDrawingEnabled();
        
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

    private void ocultarTablero(){
        
        
        if (tablero.getListaCabecera().getNodoInicio()!=null){
            
            NodoCabeceraTablero cabecera =  tablero.getListaCabecera().getNodoInicio();
            
            while (cabecera != null){
            
                int x = cabecera.getPosicionX();

                if (cabecera.getNodoInicioTablero()!=null){
                    NodoTablero celda = cabecera.getNodoInicioTablero();

                    while (celda != null){
                        
                        panelTablero.remove(celda.getLabel());
                        
                        celda = celda.getAbajo();
                    }
                }
                cabecera = cabecera.getDerecha();
            }
            
        }
        
        
        
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
                        
                        String img = "empty.png";
                        switch(celda.getMultiplicador()){
                            case 1: img = "empty.png";
                                break;
                            case 2: img = "x2.png";
                                break;
                            case 3: img = "x3.png";
                                break;
                            
                        }
                        
                        lbl.setIcon(new ImageIcon(getClass().getResource("/scrabble/images/"+img)));
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
        
        lblNombreJugador.setText(jugadorActual.getNombre());
    
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
        //this.pack()
        panelTablero.repaint();
        
    }
    
    private void mostrarPanelGraphviz(String imagePath){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmGraph it = new frmGraph(imagePath);
        frame.add(it);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
    }
    
    private void siguienteJugador(){
        
        //remover las fichas del jugador actual
        
        //Recorrer la lista de fichas y quitarlas una a una
        ListaFichasJugador fichasJugador = jugadorActual.getFichas();
        
        JOptionPane.showMessageDialog(this, "Turno del jugador " + jugadorActual.getNombre(), "Cambio de turno", JOptionPane.INFORMATION_MESSAGE);
        
        NodoFicha nodo = fichasJugador.getInicio();
        int numero = 0;
        while (nodo!=null){
            numero++;
            Ficha ficha = nodo.getFicha();
            
            panelTablero.remove(ficha.getLabel());
            
            //movernos a la siguiente ficha
            nodo = nodo.getSiguiente();
        }   
        
        //mover al siguiente jugador
        listaJugadores.moverAlSiguiente();
        
        ocultarTablero();
                
        mostrarFichasJugador( );
        
        mostrarTablero();
        
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
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnListaJugadores = new javax.swing.JButton();
        btnColaDeFichas = new javax.swing.JButton();
        btnListaPalabras = new javax.swing.JButton();
        btnListaFichasActivas = new javax.swing.JButton();
        btnMatrizTablero = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarPalabra = new javax.swing.JButton();
        txtAgregarPalabra = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNombreJugador = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnValidarTiro = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnSiguienteJugador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTablero.setOpaque(false);
        panelTablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("REPORTES");

        btnListaJugadores.setText("Ver Lista de Jugadores");
        btnListaJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaJugadoresActionPerformed(evt);
            }
        });

        btnColaDeFichas.setText("Ver Cola de Fichas");
        btnColaDeFichas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColaDeFichasActionPerformed(evt);
            }
        });

        btnListaPalabras.setText("Ver Lista de Palabras");
        btnListaPalabras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaPalabrasActionPerformed(evt);
            }
        });

        btnListaFichasActivas.setText("Ver Lista de Fichas Activa");
        btnListaFichasActivas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaFichasActivasActionPerformed(evt);
            }
        });

        btnMatrizTablero.setText("Ver Matriz del Tablero");
        btnMatrizTablero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatrizTableroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListaPalabras)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnListaJugadores)
                                    .addComponent(btnColaDeFichas))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMatrizTablero)
                                    .addComponent(btnListaFichasActivas))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnListaFichasActivas)
                    .addComponent(btnListaJugadores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnColaDeFichas)
                    .addComponent(btnMatrizTablero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListaPalabras)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setText("DICCIONARIO");

        btnAgregarPalabra.setText("Agregar Palabra");
        btnAgregarPalabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPalabraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAgregarPalabra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarPalabra)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarPalabra)
                    .addComponent(txtAgregarPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel2.setText("TURNO DEL JUGADOR");

        lblNombreJugador.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(lblNombreJugador)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreJugador)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnValidarTiro.setText("Validar Tiro");
        btnValidarTiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarTiroActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar Tiro");

        btnSiguienteJugador.setText("Siguiente Jugador");
        btnSiguienteJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteJugadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnValidarTiro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnSiguienteJugador)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValidarTiro)
                    .addComponent(jButton2)
                    .addComponent(btnSiguienteJugador))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEstadisticoLayout = new javax.swing.GroupLayout(panelEstadistico);
        panelEstadistico.setLayout(panelEstadisticoLayout);
        panelEstadisticoLayout.setHorizontalGroup(
            panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadisticoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelEstadisticoLayout.setVerticalGroup(
            panelEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEstadisticoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListaJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaJugadoresActionPerformed

        //creamos el objeto graphviz
        String imagePath = listaJugadores.crearImagenGraphviz();
        System.out.println(imagePath);
        mostrarPanelGraphviz(imagePath);
        
        
    }//GEN-LAST:event_btnListaJugadoresActionPerformed

    private void btnColaDeFichasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColaDeFichasActionPerformed
        
        //creamos el objeto graphviz
        String imagePath = colaDeFichas.crearImagenGraphviz();
        System.out.println(imagePath);
        mostrarPanelGraphviz(imagePath);
    }//GEN-LAST:event_btnColaDeFichasActionPerformed

    private void btnListaPalabrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaPalabrasActionPerformed
        //creamos el objeto graphviz
        String imagePath = diccionario.crearImagenGraphviz();
        System.out.println(imagePath);
        mostrarPanelGraphviz(imagePath);
    }//GEN-LAST:event_btnListaPalabrasActionPerformed

    private void btnListaFichasActivasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaFichasActivasActionPerformed
        //creamos el objeto graphviz
        String imagePath = jugadorActual.getFichas().crearImagenGraphviz();
        System.out.println(imagePath);
        mostrarPanelGraphviz(imagePath);
    }//GEN-LAST:event_btnListaFichasActivasActionPerformed

    private void btnAgregarPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPalabraActionPerformed
        
        if (txtAgregarPalabra.getText().compareTo("")!=0){
            
            if (txtAgregarPalabra.getText().indexOf(" ")<0){
                diccionario.agregarAlFinal(txtAgregarPalabra.getText());
                txtAgregarPalabra.setText("");
                JOptionPane.showMessageDialog(this, "Se agregó la palabra al diccionario exitosamente.", "Adición Correcta", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "No puedes agregar mas de 1 palabra al mismo tiempo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Por favor escribe una palabra.", "Error", JOptionPane.ERROR_MESSAGE);
                    
        }
        
        
    }//GEN-LAST:event_btnAgregarPalabraActionPerformed

    private void btnMatrizTableroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatrizTableroActionPerformed
        //creamos el objeto graphviz
        String imagePath = tablero.crearImagenGraphviz(scrabble.getDimension());
        System.out.println(imagePath);
        mostrarPanelGraphviz(imagePath);
    }//GEN-LAST:event_btnMatrizTableroActionPerformed

    private void btnSiguienteJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteJugadorActionPerformed
        siguienteJugador();
    }//GEN-LAST:event_btnSiguienteJugadorActionPerformed

    private void btnValidarTiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarTiroActionPerformed
        
        //tomamos las fichas que estan en el tablero (posicionEnTablero!=null)
        
        
        //recorrer el tablero de izq a derecha y luego de arriba para abajo
        
        //de arriba para abajo
        
        for (int x=0; x<scrabble.getDimension(); x++){
            
            int puntos = 0;
            String palabra = "";
            boolean hayInteraccionConLaPalabra = false;
            
            for (int y=0; y<scrabble.getDimension(); y++){
                
                NodoTablero nodo = tablero.obtenerNodo(x, y);
                
                Ficha ficha = null; 
                
                
                //revisar si es cualquiera de las que acabamos de colocar
                ListaFichasJugador fichasJugador = jugadorActual.getFichas();
                NodoFicha nodoFJ = fichasJugador.getInicio();
                while (nodoFJ!=null){
                    Ficha fichaJ = nodoFJ.getFicha();
                    if (fichaJ.getPosicionEnTablero()!=null){
                        if ((fichaJ.getPosicionEnTablero().x == x) &&(fichaJ.getPosicionEnTablero().y == y)){
                            ficha = fichaJ;
                            ficha.setOcupada(true);
                            hayInteraccionConLaPalabra=true;
                        }
                    }
                    //movernos a la siguiente ficha
                    nodoFJ = nodoFJ.getSiguiente();
                }
                
                if (ficha==null){
                    //revisar si el tablero ya tiene una ficha
                    if (nodo.getFicha()!=null){
                        ficha = nodo.getFicha();
                    }
                }

                
                if (ficha != null){
                    palabra += ficha.getLetra();
                    puntos += nodo.getMultiplicador()*ficha.getPuntos();
                } 
                
                
                if ((ficha == null)||(y==scrabble.getDimension()-1)){
                    //si aún es null es porque esta posicion en el tablero está vacia
                    //por lo tanto evaluaremos si tenemos una palabra valida
                    //o bien si es la ultima letra de esa lista
                    
                    if (hayInteraccionConLaPalabra && validarPalabra(palabra)){
                        
                        jugadorActual.setPunteo(jugadorActual.getPunteo()+puntos);
                        JOptionPane.showMessageDialog(this, "Haz ganado " + puntos 
                                +" por la palabra " + palabra + ".", "Adición Correcta", JOptionPane.INFORMATION_MESSAGE);
                        //todas las fichas marcadas como ocupada pasan a ser
                        //parte del tablero
                        //ademas se le quitan al jugador
                        //y se le reparte nuevamente


                        NodoFicha nodoFJ2 = fichasJugador.getInicio();
                        while (nodoFJ2!=null){
                            Ficha fichaFJ2 = nodoFJ2.getFicha();
                            if (fichaFJ2.isOcupada()){

                                
                                Ficha fichaPermanente = new Ficha();
                                fichaPermanente.setLetra(fichaFJ2.getLetra());
                                fichaPermanente.setPuntos(fichaFJ2.getPuntos());

                                NodoTablero tmpNodo = tablero.obtenerNodo(fichaFJ2.getPosicionEnTablero().x, fichaFJ2.getPosicionEnTablero().y);
                                
                                tmpNodo.setFicha(fichaPermanente);
                                tmpNodo.getLabel().setIcon(new ImageIcon(getClass().getResource("/scrabble/images/"+fichaFJ2.getLetra()+".png")));
                                

                                jugadorActual.quitarFicha(fichaFJ2);
                                jugadorActual.agregarFicha(colaDeFichas.desencolar());

                            }
                            //movernos a la siguiente ficha
                            nodoFJ2 = nodoFJ2.getSiguiente();
                        }   

                           
                    }
                    palabra="";
                }
            }
        }
        
        for (int y=0; y<scrabble.getDimension(); y++){
            
            int puntos = 0;
            String palabra = "";
            boolean hayInteraccionConLaPalabra = false;
            
            for (int x=0; x<scrabble.getDimension(); x++){
                
                NodoTablero nodo = tablero.obtenerNodo(x, y);
                
                Ficha ficha = null; 
                
                //revisar si es cualquiera de las que acabamos de colocar
                ListaFichasJugador fichasJugador = jugadorActual.getFichas();
                NodoFicha nodoFJ = fichasJugador.getInicio();
                while (nodoFJ!=null){
                    Ficha fichaJ = nodoFJ.getFicha();
                    if (fichaJ.getPosicionEnTablero()!=null){
                        if ((fichaJ.getPosicionEnTablero().x == x) &&(fichaJ.getPosicionEnTablero().y == y)){
                            ficha = fichaJ;
                            ficha.setOcupada(true);
                            hayInteraccionConLaPalabra=true;
                        }
                    }
                    //movernos a la siguiente ficha
                    nodoFJ = nodoFJ.getSiguiente();
                }
                
                if (ficha==null){
                    //revisar si el tablero ya tiene una ficha
                    if (nodo.getFicha()!=null){
                        ficha = nodo.getFicha();
                    }
                }

                
                if (ficha != null){
                    palabra += ficha.getLetra();
                    puntos += nodo.getMultiplicador()*ficha.getPuntos();
                } 
                
                
                if ((ficha == null)||(y==scrabble.getDimension()-1)){
                    //si aún es null es porque esta posicion en el tablero está vacia
                    //por lo tanto evaluaremos si tenemos una palabra valida
                    //o bien si es la ultima letra de esa lista
                    
                    if (hayInteraccionConLaPalabra && validarPalabra(palabra)){
                        
                        jugadorActual.setPunteo(jugadorActual.getPunteo()+puntos);
                        JOptionPane.showMessageDialog(this, "Haz ganado " + puntos 
                                +" por la palabra " + palabra + ".", "Adición Correcta", JOptionPane.INFORMATION_MESSAGE);
                        //todas las fichas marcadas como ocupada pasan a ser
                        //parte del tablero
                        //ademas se le quitan al jugador
                        //y se le reparte nuevamente


                        NodoFicha nodoFJ2 = fichasJugador.getInicio();
                        while (nodoFJ2!=null){
                            Ficha fichaFJ2 = nodoFJ2.getFicha();
                            if (fichaFJ2.isOcupada()){

                                
                                Ficha fichaPermanente = new Ficha();
                                fichaPermanente.setLetra(fichaFJ2.getLetra());
                                fichaPermanente.setPuntos(fichaFJ2.getPuntos());

                                NodoTablero tmpNodo = tablero.obtenerNodo(fichaFJ2.getPosicionEnTablero().x, fichaFJ2.getPosicionEnTablero().y);
                                
                                tmpNodo.setFicha(fichaPermanente);
                                tmpNodo.getLabel().setIcon(new ImageIcon(getClass().getResource("/scrabble/images/"+fichaFJ2.getLetra()+".png")));
                                

                                jugadorActual.quitarFicha(fichaFJ2);
                                jugadorActual.agregarFicha(colaDeFichas.desencolar());

                            }
                            //movernos a la siguiente ficha
                            nodoFJ2 = nodoFJ2.getSiguiente();
                        }   

                           
                    }
                    palabra="";
                }
            }
        }
        
        siguienteJugador();
    }//GEN-LAST:event_btnValidarTiroActionPerformed

    private boolean validarPalabra(String palabra){
        NodoDiccionario nodoDiccionario = diccionario.getInicio();
        while(nodoDiccionario!=null){
            if (palabra.compareToIgnoreCase(nodoDiccionario.getPalabra().get())==0){
                return true;
            }
            nodoDiccionario = nodoDiccionario.getSiguiente();
        }
        return false;              
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPalabra;
    private javax.swing.JButton btnColaDeFichas;
    private javax.swing.JButton btnListaFichasActivas;
    private javax.swing.JButton btnListaJugadores;
    private javax.swing.JButton btnListaPalabras;
    private javax.swing.JButton btnMatrizTablero;
    private javax.swing.JButton btnSiguienteJugador;
    private javax.swing.JButton btnValidarTiro;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblNombreJugador;
    private javax.swing.JPanel panelEstadistico;
    private javax.swing.JPanel panelTablero;
    private javax.swing.JTextField txtAgregarPalabra;
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
